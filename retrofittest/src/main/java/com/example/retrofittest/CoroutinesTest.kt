package com.example.retrofittest

import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * FileName: CoroutinesTest
 * Author: Naomi
 * Date: 2020/7/9 22:47
 * Description:
 */

fun main1() {
    GlobalScope.launch {
        println("are u ok?")
        delay(1500)
        println("uuuuuuu")
    }
    Thread.sleep(1000)
}

fun main2() {
    runBlocking {
        println("are u ok?")
        delay(1500)
        println("uuuuuuuu")
    }
}

fun main3() {
    val start = System.currentTimeMillis()
    runBlocking {
        repeat(100000)
        {
            launch {
                println("are u ok?")
            }
        }
    }
    val end = System.currentTimeMillis()
    println(end - start)
}

//挂起函数，suspend关键字，若想在挂起函数中创建子协程即Launch()函数，需要使用coroutinesScope()函数
suspend fun delayyyy(time: Long) {
    println("Naomiiiii")
    delay(time)
}

suspend fun delayAndCoroutine(time: Long) = coroutineScope {
    launch {
        println("Naomiiiii")
        delay(time)
    }
}

fun main4() {
    val job = Job()
    val scope = CoroutineScope(job)
    scope.launch {
        println("i need a doctor")
    }
    job.cancel()
}

fun main5() {
    runBlocking {
        val start = System.currentTimeMillis()
        val deferred1 = async {
            delay(1000)
            5 + 5
        }
        val deferred2 = async {
            delay(1000)
            5 + 5
        }
        println("Naaaaaaa+${deferred1.await() + deferred2.await()}")
        val end = System.currentTimeMillis()
        println(end - start)
    }
}

fun main() {
    runBlocking {
        val result = withContext(Dispatchers.Default) {
            5 + 5
        }
        println(result)
    }
}

suspend fun request(address: String): String {
    return suspendCoroutine { continuation ->
        HttpUtil.sendHttpURLRequest(address, object : HttpCallbackListener {
            override fun onFinish(response: String) {
                continuation.resume(response)
            }

            override fun onError(e: Exception) {
                continuation.resumeWithException(e)
            }
        })
    }
}

suspend fun <T> Call<T>.await(): T {
    return suspendCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null)
                    continuation.resume(body)
                else
                    continuation.resumeWithException(RuntimeException("body is null"))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}