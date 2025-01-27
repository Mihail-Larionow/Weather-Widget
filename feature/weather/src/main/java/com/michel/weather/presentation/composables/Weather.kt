package com.michel.weather.presentation.composables

import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.designsystem.composables.snackbar.Snackbar
import com.michel.designsystem.composables.snackbar.SnackbarLayout
import com.michel.weather.di.WeatherComponentHolder
import com.michel.weather.presentation.mvi.entities.WeatherEffect

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
                is WeatherEffect.ShowInfoSnackbar -> Snackbar.show(
                    layout = SnackbarLayout.Toast(
                        text = effect.text,
                    ),
                    duration = SnackbarDuration.Long,
                )
            }
        }
    }

    WeatherScreen(
        state = viewModel.state.collectAsState().value,
        onIntent = viewModel::accept,
    )
}
