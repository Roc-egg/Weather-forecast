package com.hp.weatherforecast.logic.model

/**
 * 项目：    WeatherForecast
 * 类描述：
 * 创建人：  hp
 * 创建时间：2020/5/6 9:44
 * 修改人：  hp
 * 修改时间：2020/5/6 9:44
 * 修改备注：
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)