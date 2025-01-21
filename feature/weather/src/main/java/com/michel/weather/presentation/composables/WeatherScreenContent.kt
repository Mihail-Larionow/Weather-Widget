package com.michel.weather.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.michel.designsystem.theme.WeatherTheme
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherState

@Composable
internal fun WeatherScreenContent(
    state: WeatherState,
    onIntent: (WeatherIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            WeatherToolbar(
                onProfileClick = { onIntent(WeatherIntent.ProfileClicked) },
                onSettingsClick = { onIntent(WeatherIntent.SettingsClicked) },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(WeatherTheme.colors.backgroundSecondary)
        ) {
            when (state) {
                is WeatherState.Loading -> WeatherContentSkeleton(
                    modifier = Modifier.align(Alignment.Center),
                )

                is WeatherState.Loaded -> WeatherContent(
                    state = state,
                    onIntent = onIntent,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

internal val MockLoadingWeatherState = WeatherState.Loading

internal val MockLoadedWeatherState = WeatherState.Loaded(
    weatherInfo = "Погода"
)

@Preview(showBackground = true)
@Composable
private fun LoadingWeatherScreenPreview() {
    WeatherTheme {
        WeatherScreenContent(
            state = MockLoadingWeatherState,
            onIntent = { },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadedWeatherScreenPreview() {
    WeatherTheme {
        WeatherScreenContent(
            state = MockLoadedWeatherState,
            onIntent = { },
        )
    }
}
