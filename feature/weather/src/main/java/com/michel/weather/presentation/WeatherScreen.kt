package com.michel.weather.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.weather.di.WeatherComponentHolder
import com.michel.weather.presentation.composables.WeatherScreenContent
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherState

@Composable
fun WeatherScreen() {
    DisposableEffect(WeatherComponentHolder) {
        WeatherComponentHolder.get()
        onDispose { WeatherComponentHolder.clear() }
    }

    val internalApi = remember { WeatherComponentHolder.internalApi }
    val viewModel = viewModel { internalApi.viewModelFactory.create() }

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is WeatherEffect.Navigate -> internalApi.navApi.navigate(effect.direction)
            }
        }
    }

    WeatherScreen(
        state = viewModel.state.collectAsState().value,
        intentConsumer = viewModel::accept,
    )
}

@Composable
private fun WeatherScreen(
    state: WeatherState,
    intentConsumer: (WeatherIntent) -> Unit,
) {
    WeatherScreenContent(
        state = state,
        onIntent = intentConsumer,
    )
}
