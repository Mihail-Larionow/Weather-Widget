package com.michel.weatherit.main.mvi

import com.michel.mvi.store.Reducer
import com.michel.weatherit.main.mvi.entities.MainEffect
import com.michel.weatherit.main.mvi.entities.MainMessage
import com.michel.weatherit.main.mvi.entities.MainState
import javax.inject.Inject

class MainReducer @Inject constructor() : Reducer<MainEffect, MainState, MainMessage> {

    override fun reduce(message: MainMessage, prevState: MainState): MainState = when (message) {
        is MainMessage.FinishSplash,
        is MainMessage.Empty,
            -> prevState
    }

    override fun reduceEffect(message: MainMessage): MainEffect? = when (message) {
        is MainMessage.FinishSplash -> MainEffect.FinishSplash
        is MainMessage.Empty,
            -> Reducer.nothing
    }
}
