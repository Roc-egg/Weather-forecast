package com.hp.weatherforecast.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 项目：    WeatherForecast
 * 类描述：
 * 创建人：  hp
 * 创建时间：2020/4/30 17:14
 * 修改人：  hp
 * 修改时间：2020/4/30 17:14
 * 修改备注：
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}