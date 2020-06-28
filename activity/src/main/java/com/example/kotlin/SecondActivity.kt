package com.example.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //上级界面intent传递数据
        val data = intent.getStringExtra("data")
        Log.d("second", data)

        //返回数据回上级界面
        bt2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "hihihihihi")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    //重写返回键，传递数据
    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("data_return", "Hello")
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}