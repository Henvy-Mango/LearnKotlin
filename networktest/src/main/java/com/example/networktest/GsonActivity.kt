package com.example.networktest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_http_url_connection.*
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread

class GsonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_url_connection)
        bt_sendRequest.setOnClickListener {
            //sendRequestWithURLConnection()
            HttpUtil.sendOkHttpRequest("https://naomi.pub/data.json", object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    //response.body.string()只能请求一次，请求过后，就会关闭，再次调用response.body.string()就会报close异常。
                    val responseData = response.body?.string()
                    showGsonRespone(responseData ?: "[]")
                    runOnUiThread {
                        textView.text = responseData
                    }
                }
            })
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
                    showGsonRespone(responseData)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showGsonRespone(jsonData: String) {
        val gson = Gson()
        val typeOf = object : TypeToken<List<Person>>() {}.type
        val personList = gson.fromJson<List<Person>>(jsonData, typeOf)
        for (i in personList) {
            Log.d("Naomi", i.id + i.version + i.name)
        }
    }
}