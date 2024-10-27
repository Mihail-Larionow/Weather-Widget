package com.michel.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<I, E, S, M> : ViewModel() {

    abstract val state: StateFlow<S>

    abstract val effect: Flow<E>

    abstract fun handleIntent(intent: I)
}