package com.example.weatherforesight.logic

import androidx.lifecycle.liveData
import com.example.weatherforesight.logic.network.WeatherforesightNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * FileName: Repository
 * Author: Naomi
 * Date: 2020/9/2 20:15
 * Description:
 *
 */

object Repository {
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = WeatherforesightNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }


    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}