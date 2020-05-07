package com.hp.weatherforecast.logic

import androidx.lifecycle.liveData
import com.hp.weatherforecast.logic.dao.PlaceDao
import com.hp.weatherforecast.logic.model.Place
import com.hp.weatherforecast.logic.model.Weather
import com.hp.weatherforecast.logic.network.WeatherForecastNetword
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import java.lang.Exception
import java.lang.RuntimeException

/**
 * 项目：    WeatherForecast
 * 类描述：  仓库层统一封装入口
 * 创建人：  hp
 * 创建时间：2020/4/16 14:20
 * 修改人：  hp
 * 修改时间：2020/4/16 14:20
 * 修改备注：
 */
object Repository {
    /**
     * 自定义高阶函数统一处理try catch
     *
     * @param T
     * @param context
     * @param block
     */
    private fun <T> fire(context: CoroutineDispatcher, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

    /**
     * 搜索城市
     *
     * @param query
     */
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = WeatherForecastNetword.searchPlace(query)
        if (placeResponse.status == "ok") {
            val place = placeResponse.places
            Result.success(place)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        //协程作用域
        coroutineScope {
            //协程1
            val deferredRealtime = async {
                WeatherForecastNetword.getRealtimeWeather(lng, lat)
            }
            //协程2
            val deferrdeDaily = async {
                WeatherForecastNetword.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferrdeDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure<Weather>(RuntimeException("realtime response status is ${realtimeResponse.status} daily response status is ${dailyResponse.status}"))
            }
        }
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavePlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

}