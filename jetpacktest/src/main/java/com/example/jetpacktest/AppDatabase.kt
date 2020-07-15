package com.example.jetpacktest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jetpacktest.Dao.BookDao
import com.example.jetpacktest.Dao.UserDao
import com.example.jetpacktest.Entity.Book
import com.example.jetpacktest.Entity.User

/**
 * FileName: AppDatabase
 * Author: Naomi
 * Date: 2020/7/14 19:54
 * Description:
 */
@Database(version = 3, entities = [User::class, Book::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao

    companion object {

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "create table Book (id integer primary key autoincrement not null, name text not null, pages integer not null)"
                )
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "alter table Book add column author text not null default 'unknown'"
                )
            }
        }

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                .allowMainThreadQueries()   //允许主线程进行数据库操作
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()
                .apply {
                    instance = this
                }
        }
    }
}