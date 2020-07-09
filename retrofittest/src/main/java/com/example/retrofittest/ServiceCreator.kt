package com.example.retrofittest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * FileName: ServiceCreator
 * Author: Naomi
 * Date: 2020/7/9 22:30
 * Description:
 */
object ServiceCreator {
    private const val baseUrl = "https://naomi.pub/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(ServiceClass: Class<T>): T = retrofit.create(ServiceClass)

    //泛型的实化
    inline fun <reified T> create(): T = create(T::class.java)
}