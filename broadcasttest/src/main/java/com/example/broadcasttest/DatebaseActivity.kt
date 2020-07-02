package com.example.broadcasttest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_datebase.*

class DatebaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datebase)

        val dbHelper = MyyDatabaseHelper(this, "BookStore.db", 3)

        bt_create.setOnClickListener {
            dbHelper.writableDatabase
        }

        bt_add.setOnClickListener {
            val db = dbHelper.writableDatabase

            //var value1 = ContentValues().apply {
            //    put("name", "abc")
            //    put("author", "bbc")
            //    put("pages", 454)
            //    put("price", 16.99)
            //}
            //db.insert("Book", null, value1)
            //
            //var value2 = ContentValues().apply {
            //    put("name", "qqq")
            //    put("author", "aaa")
            //    put("pages", 666)
            //    put("price", 299.11)
            //}
            //db.insert("Book", null, value2)

            db.execSQL(
                    "insert into Book(name,author,pages,price)values(?,?,?,?)",
                    arrayOf("qqq", "aaa", "666", "111")
            )
            db.execSQL(
                    "insert into Book(name,author,pages,price)values(?,?,?,?)",
                    arrayOf("ccc", "ddd", "300", "333")
            )
        }

        bt_update.setOnClickListener {
            val db = dbHelper.writableDatabase

            //val value1 = ContentValues().apply {
            //    put("price", 10.1)
            //}
            //
            //db.update("Book", value1, "name=?", arrayOf("abc"))

            db.execSQL("update Book set price=? where name=?", arrayOf("100", "abc"))
        }

        bt_delete.setOnClickListener {
            val db = dbHelper.writableDatabase

            //db.delete("Book", "pages>?", arrayOf("500"))

            db.execSQL("delete from Book where pages>?", arrayOf("500"))
        }

        bt_query.setOnClickListener {
            val db = dbHelper.writableDatabase

            //var cursor = db.query("Book", null, null, null, null, null, null)

            val cursor = db.rawQuery("select * from Book", null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getString(cursor.getColumnIndex("pages"))
                    val price = cursor.getString(cursor.getColumnIndex("price"))
                    Log.d("aaaa", "$name+$author+$pages+$price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

    }
}