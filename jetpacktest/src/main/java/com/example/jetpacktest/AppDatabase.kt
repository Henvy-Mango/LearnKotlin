package com.example.jetpacktest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * FileName: AppDatabase
 * Author: Naomi
 * Date: 2020/7/14 19:54
 * Description:
 */
@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                .allowMainThreadQueries()   //允许主线程进行数据库操作
                .build()
                .apply {
                    instance = this
                }
        }
    }
}