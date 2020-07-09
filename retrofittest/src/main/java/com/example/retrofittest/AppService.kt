package com.example.retrofittest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * FileName: AppService
 * Author: Naomi
 * Date: 2020/7/9 19:07
 * Description:
 */
class App(val id: String, val name: String, val version: String)

interface AppService {
    @GET("data.json")
    fun getAppData(): Call<List<App>>

    @Headers("User-Agent: okhttp", "Cache-Control: max-age=0")
    @GET("{page}/data.json")
    fun getPageData(@Path("page") page: Int): Call<List<App>>

    @POST("data.json")
    fun postData(
        @Header("User-Agent") user_agent: String,
        @Header("Cache-Control") cache: String
    ): Call<ResponseBody>
}