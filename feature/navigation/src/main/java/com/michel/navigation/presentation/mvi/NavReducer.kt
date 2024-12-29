package com.michel.navigation.presentation.mvi

import com.michel.mvi.store.Reducer
import com.michel.navigation.presentation.mvi.entities.NavEffect
import com.michel.navigation.presentation.mvi.entities.NavMessage
import com.michel.navigation.presentation.mvi.entities.NavState
import javax.inject.Inject

internal class NavReducer @Inject constructor() : Reducer<NavEffect, NavState, NavMessage> {
    override fun reduce(message: NavMessage, prevState: NavState): NavState = when (message) {
        is NavMessage.Navigate,
        is NavMessage.Empty,
            -> prevState
    }

    override fun reduceEffect(message: NavMessage): NavEffect? = when (message) {
        is NavMessage.Navigate -> NavEffect.Navigate(message.direction)
        is NavMessage.Empty,
            -> Reducer.nothing
    }
}
