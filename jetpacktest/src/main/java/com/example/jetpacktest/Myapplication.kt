package com.example.jetpacktest

import android.app.Application
import android.content.Context
import android.widget.Toast

/**
 * FileName: Myapplication
 * Author: Naomi
 * Date: 2020/7/20 0:16
 * Description:
 */
class Myapplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(Myapplication.context, this, duration).show()
}