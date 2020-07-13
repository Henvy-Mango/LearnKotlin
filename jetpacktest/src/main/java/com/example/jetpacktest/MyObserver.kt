package com.example.jetpacktest

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * FileName: MyObserver
 * Author: Naomi
 * Date: 2020/7/13 22:52
 * Description:
 */
class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d("Naomi", "Starting!")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun activityDone() {
        Log.d("Naomi", lifecycle.currentState.toString())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d("Naomi", "Stopping!")
    }
}