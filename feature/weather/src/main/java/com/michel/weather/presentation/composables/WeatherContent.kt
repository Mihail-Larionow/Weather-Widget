package com.michel.weather.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.Skeleton
import com.michel.designsystem.composables.extensions.clickableWithRipple
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherState

@Composable
internal fun WeatherContent(
    state: WeatherState.Loaded,
    onIntent: (WeatherIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        PlanetModel(
            modifier = Modifier
                .size(256.dp)
                .clip(CircleShape)
                .background(WeatherTheme.colors.backgroundPrimary)
                .clickableWithRipple(
                    shape = CircleShape,
                    onClick = { onIntent(WeatherIntent.Reload) },
                ),
        )
        WeatherInfo(
            state = state,
        )
    }
}

@Composable
internal fun WeatherContentSkeleton(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        Skeleton(
            modifier = Modifier.size(256.dp),
            shape = CircleShape,
        )
        Skeleton(
            modifier = Modifier.size(
                width = 120.dp,
                height = 20.dp,
            ),
            shape = RoundedCornerShape(8.dp),
        )
    }
}

@Composable
private fun WeatherInfo(
    state: WeatherState.Loaded,
    modifier: Modifier = Modifier,
) {
    Text(
        text = state.weatherInfo,
        style = WeatherTheme.typography.title2,
        color = WeatherTheme.colors.textPrimary,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
private fun WeatherContentSkeletonPreview() {
    WeatherTheme {
        WeatherContentSkeleton()
    }
}

@ThemePreviews
@Composable
private fun WeatherContentPreview() {
    WeatherTheme {
        WeatherContent(
            state = MockLoadedWeatherState,
            onIntent = { },
        )
    }
}

