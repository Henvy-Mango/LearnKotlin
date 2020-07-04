package com.example.cameraalbumtest

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val takephoto = 1
        lateinit var imageUri: Uri
        lateinit var outputimage: File

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_photo.setOnClickListener {
            outputimage = File(externalCacheDir, "output_image.jpg")
        }
    }
}
