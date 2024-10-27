package com.michel.mvi

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge

@OptIn(FlowPreview::class)
fun interface Actor<I, S, M> {

    fun init(): Flow<M> = emptyFlow()

    fun run(intent: I, prevState: S): Flow<M>

    fun Flow<I>.runActor(state: S): Flow<M> = flatMapMerge { intent ->
        run(intent = intent, prevState = state)
    }
}