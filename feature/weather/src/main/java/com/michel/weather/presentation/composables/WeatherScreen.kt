package com.michel.weather.presentation.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.Skeleton
import com.michel.designsystem.composables.blur.blurredBackground
import com.michel.designsystem.composables.extensions.clickableWithRipple
import com.michel.designsystem.composables.extensions.elevation
import com.michel.designsystem.composables.extensions.isScrolled
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme
import com.michel.weather.presentation.composables.colors.weatherBackground
import com.michel.weather.presentation.composables.info.WeatherList
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherState
import dev.chrisbanes.haze.HazeState

private val DefaultCornerSize = 16.dp

@Composable
internal fun WeatherScreen(
    state: WeatherState,
    onIntent: (WeatherIntent) -> Unit,
) {
    Scaffold { paddingValues ->
        WeatherContent(
            state = state,
            onIntent = onIntent,
            modifier = Modifier
                .weatherBackground()
                .padding(paddingValues),
        )
    }
}

@Composable
private fun WeatherContent(
    state: WeatherState,
    onIntent: (WeatherIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is WeatherState.Loading -> LoadingContent(
            modifier = modifier,
        )

        is WeatherState.Loaded -> LoadedContent(
            state = state,
            onIntent = onIntent,
            modifier = modifier,
        )
    }
}

@Composable
private fun LoadingContent(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
    ) {
        Skeleton(
            modifier = Modifier.size(128.dp),
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
private fun LoadedContent(
    state: WeatherState.Loaded,
    onIntent: (WeatherIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val hazeState = remember { HazeState() }
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            MainWeatherInfo(
                state = state,
                onIntent = onIntent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
            )
        }
        item {
            WeatherList(
                items = state.temperature,
                modifier = Modifier
                    .padding(
                        vertical = 16.dp,
                        horizontal = 12.dp,
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .blurredBackground(
                        hazeState = hazeState,
                        blurBackgroundColor = WeatherTheme.colors.constantBlack.copy(alpha = 0.1f),
                        fallbackColor = WeatherTheme.colors.constantBlack,
                    ),
            )
        }
    }
}

@Composable
private fun MainWeatherInfo(
    state: WeatherState.Loaded,
    onIntent: (WeatherIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(DefaultCornerSize),
        modifier = modifier,
    ) {
        WeatherToolbar(
            onProfileClick = { onIntent(WeatherIntent.ProfileClicked) },
            onSettingsClick = { onIntent(WeatherIntent.SettingsClicked) },
        )
        WeatherIcon(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .clickableWithRipple(
                    shape = CircleShape,
                    onClick = { onIntent(WeatherIntent.Reload) },
                ),
        )
        Temperature(
            temperature = state.temperature.first().toString(),
        )
    }
}

@Composable
private fun Temperature(
    temperature: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
    ) {
        Text(
            text = temperature,
            fontWeight = FontWeight.Bold,
            style = WeatherTheme.typography.main,
            color = Color.White,
        )
        Text(
            text = WeatherTheme.strings.weather.celsiusDegree,
            fontWeight = FontWeight.Bold,
            style = WeatherTheme.typography.main,
            color = Color.White,
        )
    }
}

@ThemePreviews
@Composable
private fun LoadedWeatherScreenPreview() = WeatherTheme {
    val temperatures = listOf(30, 31, 32, 34, 31, 30, 28, 24, 20, 14, 8, 2, -2)
    WeatherScreen(
        state = WeatherState.Loaded(
            temperature = temperatures,
        ),
        onIntent = { },
    )
}

@Preview(showBackground = true)
@Composable
private fun LoadingWeatherScreenPreview() = WeatherTheme {
    WeatherScreen(
        state = WeatherState.Loading,
        onIntent = { },
    )
}
