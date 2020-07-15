package com.example.jetpacktest.Entity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * FileName: User
 * Author: Naomi
 * Date: 2020/7/14 18:25
 * Description:
 */

@Entity
data class User(var name: String, var age: Int, var desc: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

object Repository {
    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(
            userId,
            (1..100).random(),
            userId
        )
        return liveData
    }
}