package com.michel.weather.presentation.composables

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme

@Composable
internal fun WeatherIcon(
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = WeatherTheme.icons.ic64.sun,
        contentDescription = null,
        tint = Color.Yellow,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
private fun WeatherIconPreview() {
    WeatherTheme {
        WeatherIcon()
    }
}
