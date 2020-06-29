package com.example.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
    private val TAG = "LifeTime"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        Log.d(TAG, "OnCreate")

        //恢复丢失数据，用于Activity被回收
        savedInstanceState?.let { Log.d(TAG, it.getString("data")) }

        //过去式写法
        //val bt1: Button = findViewById(R.id.bt1)
        //bt1.setOnClickListener { Toast.makeText(this, "qweqwe", Toast.LENGTH_SHORT).show() }

        //Kotlin简便写法
        //bt1.setOnClickListener { Toast.makeText(this, "qweqwe", Toast.LENGTH_SHORT).show() }


        bt1.setOnClickListener {
            //写法一 显示
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data", "kkkkkkkk")
            //startActivity(intent)

            //写法二 隐式 使用action标签和category标签
            //val intent = Intent("com.example.activitytest.ACTION_START")
            //intent.addCategory("com.example.activitytest.GOGO")
            //startActivity(intent)

            //隐式Intent 调用其他Activity
            //val intent = Intent(Intent.ACTION_VIEW)
            //intent.data= Uri.parse("https://naomi.pub")
            //ThirdActivity 响应此Intent ，查看intent-filter标签
            //startActivity(intent)

            //下级界面返回数据给上级界面
            startActivityForResult(intent, 1)
        }

        btOne.setOnClickListener { startActivity(Intent(this, LifeCircleActivityOne::class.java)) }
        btTwo.setOnClickListener { startActivity(Intent(this, LifeCircleActivityTwo::class.java)) }
    }

    //Activity被回收之前调用，用于保存数据
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        val tmp = "Restore data"
        outState.putString("data", tmp)
    }

    //菜单创建
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //菜单选择监听器
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> Toast.makeText(this, "add", Toast.LENGTH_SHORT).show()
            R.id.del -> Toast.makeText(this, "del", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                val data_return = data?.let { Log.d("first", it.getStringExtra("data_return")) }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "OnRestart")
    }

}