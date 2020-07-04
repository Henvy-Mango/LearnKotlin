package com.example.notificationtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        //val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //manager.cancel(1)
    }
}