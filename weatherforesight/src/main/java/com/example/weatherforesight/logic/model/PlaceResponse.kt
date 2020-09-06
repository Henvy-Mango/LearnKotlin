package com.example.weatherforesight.logic.model

import com.google.gson.annotations.SerializedName

/**
 * FileName: PlaceResponse
 * Author: Naomi
 * Date: 2020/9/2 19:45
 * Description:
 */
data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)