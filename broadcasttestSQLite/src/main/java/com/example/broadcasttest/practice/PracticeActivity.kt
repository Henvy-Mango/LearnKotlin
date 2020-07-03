package com.example.broadcasttest.practice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcasttest.MainActivity
import com.example.broadcasttest.R
import kotlinx.android.synthetic.main.activity_practice.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


open class BaseActivity : AppCompatActivity() {
    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityConllector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter("com.example.broadcasttest.LOGOUT")
        receiver = LogoutReceiver()
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityConllector.removeActivity(this)
    }


    inner class LogoutReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent?) {
            AlertDialog.Builder(context).apply {
                setTitle("Warning")
                setMessage("You are logout!")
                setCancelable(false)
                setPositiveButton("OK") { _, _ ->
                    ActivityConllector.finshAll()
                    val intent = Intent(context, PracticeActivity::class.java)
                    context.startActivity(intent)
                }.show()
            }
        }

    }

}

class PracticeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
        val isRemeber = prefs.getBoolean("isRemember", false)
        if (isRemeber) {
            tv_account.setText(prefs.getString("account", ""))
            tv_password.setText(prefs.getString("password", ""))
            cb_remember.isChecked = true
        }

        bt_login.setOnClickListener {
            val editor = prefs.edit()
            val account = tv_account.text.toString()
            val password = tv_password.text.toString()
            if (account == "1" && password == "1" && cb_remember.isChecked) {
                editor.putBoolean("isRemember", true)
                editor.putString("account", account)
                editor.putString("password", password)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else if (account == "1" && password == "1") {
                editor.clear()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                editor.clear()
                Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
            }
            editor.apply()
        }
    }

    fun save(input: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(input)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun load() {
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            var content = StringBuilder()
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}

