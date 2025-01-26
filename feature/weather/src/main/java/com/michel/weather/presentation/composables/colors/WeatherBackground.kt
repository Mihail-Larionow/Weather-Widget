package com.michel.weather.presentation.composables.colors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michel.designsystem.theme.WeatherTheme

@Composable
internal fun Modifier.weatherBackground(
    shape: Shape = RectangleShape,
): Modifier = composed {
    val gradient = Brush.horizontalGradient(
        .0f to WeatherColors.first,
        .99f to WeatherColors.second,
    )

    this.background(
        brush = gradient,
        shape = shape,
    )
}

private object WeatherColors {
    val first = Color(0xFF00A4E4)
    val second = Color(0xFFb0e0e6)
}

@Preview
@Composable
private fun WeatherBackgroundPreview() = WeatherTheme {
    Box(
        modifier = Modifier
            .size(128.dp)
            .weatherBackground()
    )
}
