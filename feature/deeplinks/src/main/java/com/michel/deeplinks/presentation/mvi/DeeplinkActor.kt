package com.michel.deeplinks.presentation.mvi

import com.michel.deeplinks.domain.DeeplinkHandler
import com.michel.deeplinks.extensions.toDeeplinkMessage
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkIntent
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkMessage
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkState
import com.michel.mvi.store.Actor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeeplinkActor @Inject constructor(
    private val deeplinkHandler: DeeplinkHandler,
) : Actor<DeeplinkIntent, DeeplinkState, DeeplinkMessage> {

    override fun init(): Flow<DeeplinkMessage> = deeplinkHandler.actions.filterNotNull().map {
        it.toDeeplinkMessage()
    }

    override fun run(intent: DeeplinkIntent, prevState: DeeplinkState): Flow<DeeplinkMessage> =
        flowOf()
}
