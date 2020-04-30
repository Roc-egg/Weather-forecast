package com.hp.weatherforecast.logic

import androidx.lifecycle.liveData
import com.hp.weatherforecast.logic.model.Place
import com.hp.weatherforecast.logic.network.WeatherForecastNetword
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = WeatherForecastNetword.searchPlace(query)
        if (placeResponse.status == "ok") {
            val place = placeResponse.places
            Result.success(place)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    private fun <T> fire(context: CoroutineDispatcher, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}