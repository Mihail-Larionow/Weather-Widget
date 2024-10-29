package com.michel.weather.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.michel.weather.presentation.mvi.WeatherViewModel
import com.michel.weather.presentation.mvi.entities.WeatherState

@Composable
internal fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    WeatherContent(
        state = state,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun WeatherContent(
    state: WeatherState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(Color.Cyan)
    ) {
        WeatherInfo(
            state = state,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
private fun WeatherInfo(
    state: WeatherState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = state.weatherInfo,
        modifier = modifier,
    )
}

internal val MockWeatherState = WeatherState(
    weatherInfo = "Погода"
)

@Preview(showBackground = true)
@Composable
private fun WeatherScreenPreview() {
    WeatherContent(
        state = MockWeatherState,
        modifier = Modifier.fillMaxSize()
    )
}