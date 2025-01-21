package com.michel.deeplinks.data

import com.michel.deeplinks.domain.DeeplinkHandler
import com.michel.deeplinks.domain.models.Deeplink
import com.michel.deeplinks.domain.models.DeeplinkAction
import com.michel.deeplinks.domain.repositories.DeeplinkRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeeplinkHandlerImpl @Inject constructor(
    private val deeplinkRepository: DeeplinkRepository,
) : DeeplinkHandler {

    private val _actions: Channel<DeeplinkAction?> = Channel<DeeplinkAction?>(
        capacity = ACTION_BUFFER_CAPACITY,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    ).apply {
        trySend(null)
    }

    override val actions: Flow<DeeplinkAction?> = _actions.receiveAsFlow()

    override suspend fun handle(deeplink: Deeplink) {
        deeplinkRepository.parse(deeplink)
            .onSuccess { deeplinkAction ->
                _actions.send(deeplinkAction)
            }
            .onFailure {

            }
    }

    companion object {
        private const val ACTION_BUFFER_CAPACITY = 1
    }
}
