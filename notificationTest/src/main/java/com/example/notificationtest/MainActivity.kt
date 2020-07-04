package com.example.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)

            val channel2 =
                NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel2)
        }

        bt_send.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            //val notification = NotificationCompat.Builder(this, "normal")
            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("this is title")
                .setContentText("this is text")
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                .setContentIntent(pi)
                .setAutoCancel(true)
                //.setStyle(NotificationCompat.BigTextStyle().bigText("MPEG-I视频压缩算法中（）是利用反映运动的位移信息和前面某时刻的图像，预测出当前的图像。采用工具软件不同，计算机动画文件的存储格式也就不同。熵编码算法压缩图像后可以无失真地恢复原始数据"))
                //.setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.big_image)))
                .build()
            manager.notify(1, notification)
        }
    }
}
