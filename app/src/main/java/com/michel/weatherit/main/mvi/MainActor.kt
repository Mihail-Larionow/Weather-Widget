package com.michel.weatherit.main.mvi

import android.util.Log
import com.michel.mvi.store.Actor
import com.michel.weatherit.main.mvi.entities.MainIntent
import com.michel.weatherit.main.mvi.entities.MainMessage
import com.michel.weatherit.main.mvi.entities.MainState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MainActor @Inject constructor() : Actor<MainIntent, MainState, MainMessage> {

    override fun init(): Flow<MainMessage> = flow {
        emit(MainMessage.FinishSplash)
    }

    override fun run(intent: MainIntent, prevState: MainState): Flow<MainMessage> = when (intent) {
        else -> flowOf()
    }
}
