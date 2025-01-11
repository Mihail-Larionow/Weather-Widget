package com.michel.mvi.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow

fun <I, E : Any, S, M> store(
    initialState: S,
    scope: CoroutineScope,
    buildBlock: StoreDsl<I, E, S, M> .() -> Unit,
): Store<I, E, S, M> =
    StoreDsl<I, E, S, M>(initialState).apply(buildBlock).buildStore(scope)

class StoreDsl<I, E : Any, S, M>(
    private val initialState: S,
) {

    var actor: Actor<I, S, M> = Actor { _, _ -> emptyFlow() }
    var reducer: Reducer<E, S, M> = Reducer { _, state -> state }

    fun buildStore(
        scope: CoroutineScope,
    ): Store<I, E, S, M> {
        return Store(
            initialState = initialState,
            actor = actor,
            reducer = reducer,
            defaultSharingScope = scope,
            sharingStrategy = SharingStarted.WhileSubscribed(0),
        )
    }
}
