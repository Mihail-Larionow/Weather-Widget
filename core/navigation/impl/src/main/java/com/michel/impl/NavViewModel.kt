package com.michel.impl

import kotlinx.coroutines.flow.Flow

interface NavViewModel<R : Any> {

    val startDestinationRoute: R

    val navMessages: Flow<NavMessage<out R>>
}
