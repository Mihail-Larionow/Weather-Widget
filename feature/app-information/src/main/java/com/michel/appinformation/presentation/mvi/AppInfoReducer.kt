package com.michel.appinformation.presentation.mvi

import com.michel.appinformation.presentation.mvi.entities.AppInfoEffect
import com.michel.appinformation.presentation.mvi.entities.AppInfoMessage
import com.michel.appinformation.presentation.mvi.entities.AppInfoState
import com.michel.mvi.store.Reducer
import javax.inject.Inject

class AppInfoReducer @Inject constructor() : Reducer<AppInfoEffect, AppInfoState, AppInfoMessage> {
    override fun reduce(message: AppInfoMessage, prevState: AppInfoState): AppInfoState =
        when (message) {
            is AppInfoMessage.Navigate,
            is AppInfoMessage.Empty,
                -> prevState
        }

    override fun reduceEffect(message: AppInfoMessage): AppInfoEffect? = when (message) {
        is AppInfoMessage.Navigate -> AppInfoEffect.Navigate(message.direction)
        is AppInfoMessage.Empty,
            -> Reducer.nothing
    }
}
