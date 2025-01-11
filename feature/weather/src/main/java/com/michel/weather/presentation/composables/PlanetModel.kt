package com.michel.weather.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme

private const val MODEL_PATH: String = "model/planet.glb"

@Composable
internal fun PlanetModel(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {

    }
}

@ThemePreviews
@Composable
private fun WeatherModelPreview() {
    WeatherTheme {
        PlanetModel()
    }
}
