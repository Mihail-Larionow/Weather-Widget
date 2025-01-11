package com.michel.utils.coroutines.trickle

interface MutableTrickle<T> : Trickle<T> {
    fun put(value: T)
}

fun <T> MutableTrickle(): MutableTrickle<T> = MutableTrickleImpl()
