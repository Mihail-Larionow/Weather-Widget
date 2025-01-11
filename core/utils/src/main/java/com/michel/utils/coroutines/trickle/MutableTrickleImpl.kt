package com.michel.utils.coroutines.trickle

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MutableTrickleImpl<T> : MutableTrickle<T> {

    private val channel = Channel<T>(capacity = Channel.UNLIMITED)

    override fun put(value: T) {
        channel.trySend(value)
    }

    override fun toFlow(): Flow<T> = flow {
        for (element in channel) {
            emit(element)
        }
    }
}
