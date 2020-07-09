package com.example.networktest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_http_url_connection.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HttpHRLConnectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_url_connection)
        bt_sendRequest.setOnClickListener {
            //sendRequestWithURLConnection()

            //HttpURLConnection回调方式
            HttpUtil.sendHttpURLRequest("https://naomi.pub", object : HttpCallbackListener {
                override fun onFinish(response: String) {
                    showRespone(response)
                }

                override fun onError(e: Exception) {
                    e.printStackTrace()
                }
            })
        }
    }

    private fun sendRequestWithURLConnection() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://naomi.pub")
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
                showRespone(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showRespone(response: String) {
        runOnUiThread {
            textView.text = response
        }
    }
}
