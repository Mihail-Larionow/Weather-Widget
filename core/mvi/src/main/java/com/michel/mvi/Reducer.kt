package com.michel.mvi

fun interface Reducer<E, S, M> {

    fun reduce(message: M, prevState: S): S

    fun reduceEffect(message: M): E? = null

    companion object {
        val nothing = null
    }
}