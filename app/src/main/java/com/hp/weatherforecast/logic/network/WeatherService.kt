package com.hp.weatherforecast.logic.network

import com.hp.weatherforecast.MyApplication
import com.hp.weatherforecast.logic.model.DailyResponse
import com.hp.weatherforecast.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 项目：    WeatherForecast
 * 类描述：
 * 创建人：  hp
 * 创建时间：2020/5/6 10:03
 * 修改人：  hp
 * 修改时间：2020/5/6 10:03
 * 修改备注：
 */
interface WeatherService {
    /**
     * 获取实时的天气信息
     *
     * @param lng 经度
     * @param lat 纬度
     * @return
     */
    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>

    /**
     * 获取未来的天气信息
     *
     * @param lng 经度
     * @param lat 纬度
     * @return
     */
    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<DailyResponse>
}