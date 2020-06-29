package com.example.junior.until

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.example.junior.R
import kotlinx.android.synthetic.main.title.view.*

/**
 * FileName: TitleLayout
 * Author: Naomi
 * Date: 2020/6/29 18:04
 * Description:
 */
class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title, this)
        bt_back.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
        bt_edit.setOnClickListener {
            Toast.makeText(context, "edit button", Toast.LENGTH_SHORT).show()
        }
    }
}