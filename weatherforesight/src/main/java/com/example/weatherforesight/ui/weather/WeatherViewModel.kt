package com.example.weatherforesight.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherforesight.logic.Repository
import com.example.weatherforesight.logic.model.Location

/**
 * FileName: WeatherViewModel
 * Author: Naomi
 * Date: 2020/9/5 20:09
 * Description:
 */
class WeatherViewModel : ViewModel() {
    private val locationLiveData =
        MutableLiveData<Location>()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat, placeName)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

}