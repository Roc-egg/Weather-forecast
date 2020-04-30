package com.hp.weatherforecast.logic.network

import com.hp.weatherforecast.MyApplication
import com.hp.weatherforecast.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 项目：    WeatherForecast
 * 类描述：  城市搜索请求
 * 创建人：  hp
 * 创建时间：2020/4/15 17:36
 * 修改人：  hp
 * 修改时间：2020/4/15 17:36
 * 修改备注：https://api.caiyunapp.com/v2/place?query=北京
 */
interface PlaceService {
    @GET("v2/place")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}