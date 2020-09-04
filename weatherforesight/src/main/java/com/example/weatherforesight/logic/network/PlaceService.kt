package com.example.weatherforesight.logic.network

import com.example.weatherforesight.WeatherforesightApplication
import com.example.weatherforesight.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * FileName: PlaceService
 * Author: Naomi
 * Date: 2020/9/2 19:51
 * Description:
 */

interface PlaceService {
    @GET("v2/place?token=${WeatherforesightApplication.token}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}