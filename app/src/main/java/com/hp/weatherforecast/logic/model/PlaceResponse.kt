package com.hp.weatherforecast.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 项目：    WeatherForecast
 * 类描述：  搜索城市返回的数据对象
 * 创建人：  hp
 * 创建时间：2020/4/15 17:11
 * 修改人：  hp
 * 修改时间：2020/4/15 17:11
 * 修改备注：
 */
data class PlaceResponse(
    val places: List<Place> = listOf(),
    val query: String = "",
    val status: String = ""
)

data class Place(
    val formatted_address: String = "",
    val id: String = "",
    val location: Location = Location(),
    val name: String = "",
    val place_id: String = ""
)

data class Location(
    val lng: String = "0.0",
    val lat: String = "0.0"
)