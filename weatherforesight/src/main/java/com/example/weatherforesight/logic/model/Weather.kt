package com.example.weatherforesight.logic.model

/**
 * FileName: Weather
 * Author: Naomi
 * Date: 2020/9/5 19:34
 * Description:
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)