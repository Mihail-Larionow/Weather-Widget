package com.michel.deeplinks.presentation

import androidx.lifecycle.ViewModel
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkEffect
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkIntent
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkMessage
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkState
import com.michel.mvi.store.StoreViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DeeplinkViewModel @Inject constructor(
    storeViewModel: StoreViewModel<DeeplinkIntent, DeeplinkEffect, DeeplinkState, DeeplinkMessage>,
) : ViewModel() {

    val state: StateFlow<DeeplinkState> = storeViewModel.state

    val effects: Flow<DeeplinkEffect> = storeViewModel.effects
}

fun interface DeeplinkViewModelFactory {
    fun create(): DeeplinkViewModel
}
