package com.example.contentprovidertest

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_add.setOnClickListener {
            val uri = Uri.parse("content://com.example.contentprovider/book")
            var values = contentValuesOf(
                "name" to "aaaa",
                "price" to 222.33,
                "author" to "naomi",
                "pages" to 500
            )
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
        }

        bt_query.setOnClickListener {
            val uri = Uri.parse("content://com.example.contentprovider/book")
            contentResolver.query(uri, null, null, null, null)?.apply {
                while (moveToNext()) {
                    val name = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val price = getString(getColumnIndex("price"))
                    val pages = getString(getColumnIndex("pages"))
                    Log.d("Naomi", "$name+$author+$price+$pages")
                }
                close()
            }
        }

        bt_update.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse("content://com.example.contentprovider/book/$it")
                val values = contentValuesOf("name" to "qqqq", "pages" to 1000)
                contentResolver.update(uri, values, null, null)
            }
        }

        bt_delete.setOnClickListener {
            val uri = Uri.parse("content://com.example.contentprovider/book")
            contentResolver.delete(uri, null, null)
        }
    }
}
