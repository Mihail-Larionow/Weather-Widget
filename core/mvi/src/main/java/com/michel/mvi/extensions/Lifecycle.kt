package com.michel.mvi.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.michel.mvi.view.StoreOutputContract
import kotlinx.coroutines.launch

fun <S, E> LifecycleOwner.collectWhenStarted(
    storeOutputContract: StoreOutputContract<S, E>,
    effectConsumer: (E) -> Unit,
) {
    storeOutputContract.collectInLifecycle(
        lifecycleOwner = this,
        lifecycleState = Lifecycle.State.STARTED,
        effectConsumer = effectConsumer,
    )
}

fun <S, E> StoreOutputContract<S, E>.collectInLifecycle(
    lifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State,
    effectConsumer: (E) -> Unit,
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(lifecycleState) {
            launch { effects.collect { effectConsumer(it) } }
        }
    }
}
