package com.michel.deeplinks.presentation.mvi

import com.michel.deeplinks.presentation.mvi.entities.DeeplinkEffect
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkMessage
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkState
import com.michel.mvi.store.Reducer
import javax.inject.Inject

class DeeplinkReducer @Inject constructor() : Reducer<DeeplinkEffect, DeeplinkState, DeeplinkMessage> {

    override fun reduce(message: DeeplinkMessage, prevState: DeeplinkState) = when (message) {
        is DeeplinkMessage.Navigate,
        is DeeplinkMessage.Empty,
            -> prevState
    }

    override fun reduceEffect(message: DeeplinkMessage) = when (message) {
        is DeeplinkMessage.Navigate -> DeeplinkEffect.Navigate(message.direction)
        is DeeplinkMessage.Empty,
            -> Reducer.nothing
    }
}
