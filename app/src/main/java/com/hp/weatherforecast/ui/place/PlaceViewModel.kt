package com.hp.weatherforecast.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hp.weatherforecast.logic.Repository
import com.hp.weatherforecast.logic.model.Place

/**
 * 项目：    WeatherForecast
 * 类描述：
 * 创建人：  hp
 * 创建时间：2020/4/16 14:36
 * 修改人：  hp
 * 修改时间：2020/4/16 14:36
 * 修改备注：
 */
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = mutableListOf<Place?>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavedPlace() = Repository.getSavedPlace()
    fun isPlaceSaved() = Repository.isPlaceSaved()
}