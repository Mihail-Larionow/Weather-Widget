package com.michel.mvi.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michel.utils.extensions.lazyUnsafe
import com.michel.mvi.view.StoreInputContract
import com.michel.mvi.view.StoreOutputContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StoreViewModel<I, E, S, M>(
    private val store: Store<I, E, S, M>,
    sharingStrategy: SharingStarted = SharingStarted.Eagerly,
) : ViewModel(), StoreInputContract<I>, StoreOutputContract<S, E> {

    override val state: StateFlow<S> by lazyUnsafe {
        store.stateIn(viewModelScope, sharingStrategy, store.state)
    }

    override val effects: Flow<E> by lazyUnsafe {
        store.effects.receiveAsFlow()
    }

    override fun accept(intent: I) {
        viewModelScope.launch {
            store.emit(intent)
        }
    }
}

fun interface StoreViewModelFactory<I, E, S, M> {
    fun create(): StoreViewModel<I, E, S, M>
}
