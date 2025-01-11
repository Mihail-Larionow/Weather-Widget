package com.michel.weather.presentation

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
import com.michel.weather.di.WeatherComponentHolder
import com.michel.weather.di.WeatherInternalApi
import com.michel.weather.presentation.composables.WeatherScreenContent
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState

@Composable
fun WeatherScreen() {
    val internalApi = remember { WeatherComponentHolder.internalApi }
    DisposableEffect(WeatherComponentHolder) {
        WeatherComponentHolder.get()
        onDispose {
            WeatherComponentHolder.clear()
        }
    }
    WeatherScreen(
        viewModel = viewModel<StoreViewModel<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage>> {
            internalApi.viewModelFactory.create()
        },
        internalApi = internalApi,
    )
}

@Composable
private fun WeatherScreen(
    viewModel: StoreViewModel<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage>,
    internalApi: WeatherInternalApi,
) {
    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is WeatherEffect.Navigate -> internalApi.navApi.navigate(effect.direction)
            }
        }
    }
    WeatherScreen(
        state = viewModel.state.collectAsState(),
        intentConsumer = viewModel::accept,
    )
}

@Composable
private fun WeatherScreen(
    state: State<WeatherState>,
    intentConsumer: (WeatherIntent) -> Unit,
) {
    WeatherScreenContent(
        state = state.value,
        onIntent = intentConsumer,
        modifier = Modifier.fillMaxSize()
    )
}
