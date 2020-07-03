package com.example.contentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * FileName: MyDatabaseHelper
 * Author: Naomi
 * Date: 2020/7/4 0:23
 * Description:
 */
class MyDatabaseHelper(context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table Book(id integer primary key autoincrement,author text,price real,pages integer,name text)")
        db?.execSQL("create table Category(id integer primary key autoincrement,category_name text,category_code integer)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}