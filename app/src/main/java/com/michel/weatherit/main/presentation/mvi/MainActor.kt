package com.michel.weatherit.main.presentation.mvi

import android.content.Intent
import android.util.Log
import com.michel.deeplinks.domain.DeeplinkHandler
import com.michel.deeplinks.domain.models.Deeplink
import com.michel.mvi.store.Actor
import com.michel.weatherit.main.presentation.mvi.entities.MainIntent
import com.michel.weatherit.main.presentation.mvi.entities.MainMessage
import com.michel.weatherit.main.presentation.mvi.entities.MainState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MainActor @Inject constructor(
    private val deeplinkHandler: DeeplinkHandler,
) : Actor<MainIntent, MainState, MainMessage> {

    override fun init(): Flow<MainMessage> = flowOf(MainMessage.FinishSplash)

    override fun run(intent: MainIntent, prevState: MainState): Flow<MainMessage> = when (intent) {
        is MainIntent.HandleSystemIntent -> handleSystemIntent(intent.systemIntent)
    }

    private fun handleSystemIntent(intent: Intent): Flow<MainMessage> = flow {
        Log.i("testtag", "intent = $intent")
        if (intent.isDeeplink()) {
            val dataString = intent.dataString
            if (dataString != null) {
                val deeplink = Deeplink(dataString)
                deeplinkHandler.handle(deeplink)
            }
        }
    }

    companion object {
        private fun Intent.isDeeplink(): Boolean = action == Intent.ACTION_VIEW
    }
}
