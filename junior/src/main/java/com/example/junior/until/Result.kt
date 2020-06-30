package com.example.junior.until

/**
 * FileName: Result
 * Author: Naomi
 * Date: 2020/6/30 11:34
 * Description:
 */

sealed class Result
class Success(val msg: String) : Result()
class Failure(val err: Exception) : Result()

fun getResultMsg(result: Result) = when (result) {
    is Success -> result.msg
    is Failure -> result.err.message
}