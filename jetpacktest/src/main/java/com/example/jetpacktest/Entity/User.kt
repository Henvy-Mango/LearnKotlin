package com.example.jetpacktest.Entity

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * FileName: User
 * Author: Naomi
 * Date: 2020/7/14 18:25
 * Description:
 */

@Parcelize
//实现Intent传递对象，常用方法为Parceable，若传递的所有数据都封装在对象的主构造函数中，添加注解@Parcelize，效率高速度快，使用intent.getParcelableExtra取出。
//若传递的所有数据没有都封装在对象的主构造函数中，则要实现Parcelable接口，重写describeContents和wirteToParcel，再提供一个名为CREATOR的匿名类实现Parcelable.Creator接口，重写createFromParcel和newArray的方法
//另一种传递方法为序列化Serializable，实现Serializable接口，使用intent.getSerializableExtra取出，速度慢，因为需要花费时间进行序列化。

@Entity    //Room实体类注解
data class User(var name: String, var age: Int, var desc: String) : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

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