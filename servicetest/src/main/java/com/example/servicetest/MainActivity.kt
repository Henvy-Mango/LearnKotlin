package com.example.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProcess()
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_start.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }
        bt_stop.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }
        bt_bind.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        bt_unbind.setOnClickListener {
            unbindService(connection)
        }
        bt_startIntentService.setOnClickListener {
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
    }
}