package com.michel.weather.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michel.weather.presentation.MockWeatherListeners
import com.michel.weather.presentation.WeatherListeners
import com.michel.weather.presentation.mvi.entities.WeatherState

@Composable
internal fun WeatherScreenContent(
    state: WeatherState,
    listeners: WeatherListeners,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.testTag(""),
    ) { paddingValues ->
        WeatherContent(
            state = state,
            listeners = listeners,
            paddingValues = paddingValues,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
private fun WeatherContent(
    state: WeatherState,
    listeners: WeatherListeners,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(Color.Cyan)
    ) {
        WeatherInfo(
            state = state,
            modifier = Modifier.align(Alignment.Center),
        )

        ProfileButton(
            onClick = listeners.onProfileClick,
            modifier = Modifier.align(Alignment.TopStart)
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

@Composable
private fun ProfileButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .background(Color.Green)
            .clickable { onClick() }

    )
}

@Preview(showBackground = true)
@Composable
private fun WeatherScreenPreview() {
    WeatherScreenContent(
        state = MockWeatherState,
        listeners = MockWeatherListeners,
        modifier = Modifier.fillMaxSize()
    )
}
