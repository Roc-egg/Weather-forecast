package com.hp.weatherforecast.logic.network

import android.util.Log
import com.ihsanbal.logging.LoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * 项目：    WeatherForecast
 * 类描述：  网络数据访问入口
 * 创建人：  hp
 * 创建时间：2020/4/16 11:05
 * 修改人：  hp
 * 修改时间：2020/4/16 11:05
 * 修改备注：
 */
object WeatherForecastNetword {
    private val placeService = ServiceCreator.create(PlaceService::class.java)

    suspend fun searchPlace(query: String) = placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })

        }
    }
}