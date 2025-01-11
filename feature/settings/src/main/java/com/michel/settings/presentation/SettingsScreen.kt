package com.michel.settings.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.mvi.store.StoreViewModel
import com.michel.settings.di.SettingsComponentHolder
import com.michel.settings.di.SettingsInternalApi
import com.michel.settings.presentation.composables.SettingsScreenContent
import com.michel.settings.presentation.mvi.entities.SettingsEffect
import com.michel.settings.presentation.mvi.entities.SettingsIntent
import com.michel.settings.presentation.mvi.entities.SettingsMessage
import com.michel.settings.presentation.mvi.entities.SettingsState

@Composable
fun SettingsScreen() {
    val internalApi = remember { SettingsComponentHolder.internalApi }
    DisposableEffect(SettingsComponentHolder) {
        SettingsComponentHolder.get()
        onDispose {
            SettingsComponentHolder.clear()
        }
    }
    SettingsScreen(
        viewModel = viewModel<StoreViewModel<SettingsIntent, SettingsEffect, SettingsState, SettingsMessage>> {
            internalApi.viewModelFactory.create()
        },
        internalApi = internalApi,
    )
}


@Composable
private fun SettingsScreen(
    viewModel: StoreViewModel<SettingsIntent, SettingsEffect, SettingsState, SettingsMessage>,
    internalApi: SettingsInternalApi,
) {
    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is SettingsEffect.Navigate -> internalApi.navApi.navigate(effect.direction)
            }
        }
    }
    SettingsScreen(
        state = viewModel.state.collectAsState(),
        intentConsumer = viewModel::accept,
    )
}

@Composable
private fun SettingsScreen(
    state: State<SettingsState>,
    intentConsumer: (SettingsIntent) -> Unit,
) {
    SettingsScreenContent(
        state = state.value,
        onIntent = intentConsumer,
        modifier = Modifier.fillMaxSize()
    )
}
