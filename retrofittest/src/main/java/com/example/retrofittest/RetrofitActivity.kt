package com.example.retrofittest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_get.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://naomi.pub/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService = retrofit.create(AppService::class.java)
            appService.getAppData().enqueue(object : Callback<List<App>> {
                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                    val list = response.body()
                    if (list != null) {
                        for (app in list)
                            Log.d("Naomi", app.id + app.name + app.version)
                    }
                }

                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}
