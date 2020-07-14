package com.example.jetpacktest

import androidx.room.*

/**
 * FileName: UserDao
 * Author: Naomi
 * Date: 2020/7/14 19:30
 * Description:
 */
@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newuser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from User where age > :age")
    fun loadUserOlderThan(age: Int): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User")
    fun deleteAllUser()

    @Query("delete from User where name = :name")
    fun deleteUserByName(name: String): Int
}