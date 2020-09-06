package com.example.weatherforesight.logic.network

import com.example.weatherforesight.WeatherForesightApplication
import com.example.weatherforesight.logic.model.DailyResponse
import com.example.weatherforesight.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * FileName: WeatherService
 * Author: Naomi
 * Date: 2020/9/5 19:36
 * Description:
 */

interface WeatherService {
    @GET("v2.5/${WeatherForesightApplication.token}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>

    @GET("v2.5/${WeatherForesightApplication.token}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

}