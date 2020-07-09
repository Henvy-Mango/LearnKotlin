package com.example.retrofittest

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * FileName: HttpUtil
 * Author: Naomi
 * Date: 2020/7/8 0:01
 * Description:
 */

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}

object HttpUtil {

    fun sendOkHttpRequest(address: String, callback: okhttp3.Callback) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(address)
            .build()
        client.newCall(request).enqueue(callback)
    }

    fun sendHttpURLRequest(address: String, listener: HttpCallbackListener) {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                //回调onFinish()方法
                listener.onFinish(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                listener.onError(e)
            } finally {
                connection?.disconnect()
            }
        }
    }
}