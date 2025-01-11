package com.michel.impl

sealed interface NavMessage<R : Any> {
    data object Up : NavMessage<Nothing>
    data class Navigate<R : Any>(val route: R) : NavMessage<R>
}
