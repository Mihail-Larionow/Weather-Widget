package com.michel.impl

sealed interface NavMessage<ROUTE : Any> {
    data object Up : NavMessage<Nothing>
    data class Navigate<ROUTE : Any>(val route: ROUTE) : NavMessage<ROUTE>
}
