package com.example.fragmentactivity.NewsApp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentactivity.R
import kotlinx.android.synthetic.main.activity_news_content.*

class NewsContentActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Context, news: News) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("new_title", news.title)
                putExtra("new_content", news.content)
            }
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)

        val title = intent.getStringExtra("new_title")
        val content = intent.getStringExtra("new_content")
        if (title != null && content != null) {
            val fragment = newsContentFrag as NewsContentFragment
            fragment.refresh(News(title, content))
        }
    }
}