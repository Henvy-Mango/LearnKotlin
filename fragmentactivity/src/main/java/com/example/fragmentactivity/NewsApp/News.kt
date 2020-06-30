package com.example.fragmentactivity.NewsApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentactivity.R
import kotlinx.android.synthetic.main.news_content_frag.*

/**
 * FileName: News
 * Author: Naomi
 * Date: 2020/7/1 0:35
 * Description:
 */

class News(val title: String, val content: String)

class NewsContentFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_content_frag, container, false)
    }

    fun refresh(news: News) {
        contentLayout.visibility = View.VISIBLE
        newsTitle.text = news.title
        newsContent.text = news.content
    }

}
