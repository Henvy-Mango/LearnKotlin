package com.example.weatherforesight.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.weatherforesight.WeatherForesightApplication
import com.example.weatherforesight.logic.model.Place
import com.google.gson.Gson

/**
 * FileName: PlaceDao
 * Author: Naomi
 * Date: 2020/9/7 21:48
 * Description:

 */
object PlaceDao {
    private fun sharePreferences() =
        WeatherForesightApplication.context.getSharedPreferences(
            "WeatherForesight",
            Context.MODE_PRIVATE
        )

    fun savePlace(place: Place) {
        sharePreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getPlace(): Place {
        val placeGson = sharePreferences().getString("place", "")
        return Gson().fromJson(placeGson, Place::class.java)
    }

    fun isPlaceSaved() = sharePreferences().contains("place")
}