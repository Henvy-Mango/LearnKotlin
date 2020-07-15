package com.example.jetpacktest.WorkManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * FileName: SimpleWorker
 * Author: Naomi
 * Date: 2020/7/16 1:15
 * Description:
 */
class SimpleWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        Log.d("Naomi", "do work in SimpleWorker")

        //return Result.retry()
        return Result.success()
    }
}