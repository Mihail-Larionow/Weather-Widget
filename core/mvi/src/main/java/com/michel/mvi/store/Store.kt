package com.michel.mvi.store

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.AbstractFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import java.util.concurrent.atomic.AtomicReference

@OptIn(ExperimentalCoroutinesApi::class)
class Store<I, E, S, M>(
    initialState: S,
    private val actor: Actor<I, S, M>,
    private val reducer: Reducer<E, S, M>,
    defaultSharingScope: CoroutineScope,
    sharingStrategy: SharingStarted = SharingStarted.Eagerly,
) : AbstractFlow<S>(), FlowCollector<I> {

    private val _intentFlow = MutableSharedFlow<I>()

    private val _effectChannel = Channel<E>()
    val effects: ReceiveChannel<E> = _effectChannel

    private val atomicState = AtomicReference(initialState)
    var state: S
        get() = atomicState.get()
        private set(value) = atomicState.set(value)

    private val _state = merge(init(), observeIntents())
        .onEach { message -> produceEffects(message) }
        .map { message -> reducer.reduce(message, state) }
        .onEach { state = it }
        .onStart { emit(state) }
        .shareIn(defaultSharingScope, sharingStrategy)

    private fun init(): Flow<M> = actor.init()

    private fun Flow<I>.observeActor(): Flow<M> = with(actor) { runActor(state) }

    private fun observeIntents(): Flow<M> = _intentFlow.observeActor()

    override suspend fun collectSafely(collector: FlowCollector<S>) {
        _state.collect(collector::emit)
    }

    override suspend fun emit(value: I) {
        _intentFlow.emit(value)
    }

    private suspend fun produceEffects(message: M) {
        println("produceeffect $message")
        val effect = reducer.reduceEffect(message)
        if (effect != null) {
            _effectChannel.send(effect)
        }
    }
}
