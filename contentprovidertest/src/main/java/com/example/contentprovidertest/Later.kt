package com.example.contentprovidertest

import kotlin.reflect.KProperty

/**
 * FileName: Later
 * Author: Naomi
 * Date: 2020/7/4 14:51
 * Description:
 */


class Later<T>(val block: () -> T) {
    var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            value = block()
        }
        return value as T
    }
}

fun <T> later(block: () -> T) = Later(block)
