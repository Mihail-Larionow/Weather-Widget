package com.michel.settings.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.settings.di.SettingsComponentHolder
import com.michel.settings.presentation.mvi.entities.SettingsEffect
import com.michel.settings.presentation.mvi.entities.SettingsIntent
import com.michel.settings.presentation.mvi.entities.SettingsState

@Composable
fun Settings() {
    DisposableEffect(SettingsComponentHolder) {
        SettingsComponentHolder.get()
        onDispose {
            SettingsComponentHolder.clear()
        }
    }

    val internalApi = remember { SettingsComponentHolder.internalApi }
    val viewModel = viewModel { internalApi.viewModelFactory.create() }

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is SettingsEffect.Navigate -> internalApi.navApi.navigate(effect.direction)
            }
        }
    }

    SettingsScreen(
        state = viewModel.state.collectAsState().value,
        onIntent = viewModel::accept,
    )
}
