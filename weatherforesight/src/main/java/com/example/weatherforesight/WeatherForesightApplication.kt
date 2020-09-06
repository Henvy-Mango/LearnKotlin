package com.example.weatherforesight

import android.app.Application
import android.content.Context

/**
 * FileName: WeatherforesightApplication
 * Author: Naomi
 * Date: 2020/9/2 19:42
 * Description:
 */

class WeatherForesightApplication : Application() {
    companion object {
        lateinit var context: Context
        const val token = "NQwHhZkBy5ZflTgC"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}