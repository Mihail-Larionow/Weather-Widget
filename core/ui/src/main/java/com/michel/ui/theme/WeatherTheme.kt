package com.michel.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalWeatherColors = staticCompositionLocalOf {
    lightColors()
}

private val LocalWeatherTypography = staticCompositionLocalOf {
    WeatherTypography()
}

private val LocalWeatherIntegers = staticCompositionLocalOf {
    WeatherIntegers()
}

@Composable
fun WeatherTheme(
    colors: WeatherColors = if (isSystemInDarkTheme()) {
        darkColors()
    } else {
        lightColors()
    },
    typography: WeatherTypography = LocalWeatherTypography.current,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalWeatherColors provides colors,
        LocalWeatherTypography provides typography,
    ) {
        content()
    }
}

@Stable
object WeatherTheme {

    val colors: WeatherColors
        @ReadOnlyComposable
        @Composable
        get() = LocalWeatherColors.current

    val typography: WeatherTypography
        @ReadOnlyComposable
        @Composable
        get() = LocalWeatherTypography.current

    val integers: WeatherIntegers
        @ReadOnlyComposable
        @Composable
        get() = LocalWeatherIntegers.current
}
