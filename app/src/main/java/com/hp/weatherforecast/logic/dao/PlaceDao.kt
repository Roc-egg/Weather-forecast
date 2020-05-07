package com.hp.weatherforecast.logic.dao

import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson
import com.hp.weatherforecast.logic.model.Place

/**
 * 项目：    WeatherForecast
 * 类描述：
 * 创建人：  hp
 * 创建时间：2020/5/7 10:00
 * 修改人：  hp
 * 修改时间：2020/5/7 10:00
 * 修改备注：
 */
object PlaceDao {
    /**
     * 存储城市
     *
     * @param place
     */
    fun savePlace(place: Place) {
        SPUtils.getInstance().put("place", Gson().toJson(place))
    }

    fun getSavePlace(): Place {
        return Gson().fromJson(SPUtils.getInstance().getString("place"), Place::class.java)
    }

    fun isPlaceSaved() = SPUtils.getInstance().contains("place")
}