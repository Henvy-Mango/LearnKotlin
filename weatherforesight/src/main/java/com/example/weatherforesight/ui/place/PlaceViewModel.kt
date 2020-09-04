package com.example.weatherforesight.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherforesight.logic.Repository
import com.example.weatherforesight.logic.model.Place

/**
 * FileName: PlaceViewModel
 * Author: Naomi
 * Date: 2020/9/2 20:36
 * Description:
 */

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}