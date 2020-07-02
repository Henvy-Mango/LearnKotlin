package com.example.broadcasttest.practice

import android.app.Activity

/**
 * FileName: ActivityConllector
 * Author: Naomi
 * Date: 2020/7/1 23:57
 * Description:
 */

object ActivityConllector {

    private val activites = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activites.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activites.remove(activity)
    }

    fun finshAll() {
        for (activity in activites) {
            if (!activity.isFinishing)
                activity.finish()
        }
        activites.clear()
    }
}