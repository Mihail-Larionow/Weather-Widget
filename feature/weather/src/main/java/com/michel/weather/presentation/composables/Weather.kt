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
import com.michel.weather.presentation.models.SnackbarData
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherIntent

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
                is WeatherEffect.ShowErrorSnackbar -> Snackbar.show(
                    layout = effect.info.toSnackbarLayout(viewModel::accept),
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

private fun SnackbarData.toSnackbarLayout(
    onIntent: (WeatherIntent) -> Unit,
): SnackbarLayout = SnackbarLayout.ErrorWithButton(
    textResId = textResId,
    buttonTitleResId = button.titleResId,
    onButtonClick = { onIntent(WeatherIntent.SnackbarButtonClicked(button.action)) },
)