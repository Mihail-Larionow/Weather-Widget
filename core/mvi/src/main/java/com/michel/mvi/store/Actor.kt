package com.michel.mvi.store

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge

fun interface Actor<I, S, M> {

    fun init(): Flow<M> = emptyFlow()

    fun run(intent: I, prevState: S): Flow<M>

    @OptIn(ExperimentalCoroutinesApi::class)
    fun Flow<I>.runActor(state: S): Flow<M> = flatMapMerge { intent ->
        run(intent = intent, prevState = state)
    }
}
