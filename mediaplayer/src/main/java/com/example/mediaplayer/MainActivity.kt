package com.example.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMediaPlayer()

        bt_play.setOnClickListener {
            if (!mediaPlayer.isPlaying)
                mediaPlayer.start()
        }
        bt_pause.setOnClickListener {
            if (mediaPlayer.isPlaying)
                mediaPlayer.pause()

        }
        bt_stop.setOnClickListener {
            if (mediaPlayer.isPlaying)
                mediaPlayer.reset()
        }

    }

    private fun initMediaPlayer() {
        val assetManager = assets
        val fd = assetManager.openFd("music.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
