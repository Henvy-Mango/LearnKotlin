package com.example.networktest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_http_url_connection.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import kotlin.concurrent.thread

class JsonActivity : AppCompatActivity() {
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
                    .url("https://naomi.pub/data.json")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null)
                    showJsonRespone(responseData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showJsonRespone(jsonData: String) {
        try {
            val jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("id")
                val version = jsonObject.getString("version")
                val name = jsonObject.getString("name")
                Log.d("Naomi", "$id + $version + $name")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}