package com.example.jetpacktest.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jetpacktest.Entity.Book

/**
 * FileName: BookDao
 * Author: Naomi
 * Date: 2020/7/16 0:39
 * Description:
 */
@Dao
interface BookDao {
    @Insert
    fun insertBook(book: Book): Long

    @Query("select * from Book")
    fun loadAllBook(): List<Book>
}