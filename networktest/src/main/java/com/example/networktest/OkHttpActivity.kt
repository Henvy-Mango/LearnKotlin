package com.example.networktest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_http_url_connection.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class OkHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_url_connection)

        bt_sendRequest.setOnClickListener {
            sendRequestWithURLConnection()
        }
    }

    private fun sendRequestWithURLConnection() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://naomi.pub")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null)
                    showRespone(responseData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showRespone(response: String) {
        runOnUiThread {
            textView.text = response
        }
    }
}