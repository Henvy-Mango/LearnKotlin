package com.example.broadcasttest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 * FileName: MyyDatabaseHelper
 * Author: Naomi
 * Date: 2020/7/2 19:08
 * Description:
 */

class MyyDatabaseHelper(val context: Context, name: String, version: Int) :
        SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createBook =
                "create table Book(id integer primary key autoincrement,author text,price real,pages integer,name text)"
        val createCategory =
                "create table Category(id integer primary key autoincrement,category_name text,category_code integer)"
        db?.execSQL(createBook)
        db?.execSQL(createCategory)
        Toast.makeText(context, "Create success!", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Book")
        db?.execSQL("drop table if exists Category")
        onCreate(db)
    }

}