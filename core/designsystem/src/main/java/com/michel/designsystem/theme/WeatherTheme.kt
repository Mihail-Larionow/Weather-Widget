package com.michel.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalWeatherColors = staticCompositionLocalOf {
    lightColors()
}

private val LocalWeatherStrings = staticCompositionLocalOf {
    WeatherStrings()
}

private val LocalWeatherTypography = staticCompositionLocalOf {
    WeatherTypography()
}

private val LocalWeatherIntegers = staticCompositionLocalOf {
    WeatherIntegers()
}

private val LocalWeatherIcons = staticCompositionLocalOf {
    WeatherIcons()
}

private val LocalWeatherImages = staticCompositionLocalOf {
    WeatherImages()
}

@Composable
fun WeatherTheme(
    colors: WeatherColors = if (isSystemInDarkTheme()) {
        darkColors()
    } else {
        lightColors()
    },
    strings: WeatherStrings = LocalWeatherStrings.current,
    typography: WeatherTypography = LocalWeatherTypography.current,
    integers: WeatherIntegers = LocalWeatherIntegers.current,
    icons: WeatherIcons = LocalWeatherIcons.current,
    images: WeatherImages = LocalWeatherImages.current,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalWeatherColors provides colors,
        LocalWeatherStrings provides strings,
        LocalWeatherTypography provides typography,
        LocalWeatherIntegers provides integers,
        LocalWeatherIcons provides icons,
        LocalWeatherImages provides images,
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

    val strings: WeatherStrings
        @ReadOnlyComposable
        @Composable
        get() = LocalWeatherStrings.current

    val typography: WeatherTypography
        @ReadOnlyComposable
        @Composable
        get() = LocalWeatherTypography.current

    val integers: WeatherIntegers
        @ReadOnlyComposable
        @Composable
        get() = LocalWeatherIntegers.current

    val icons: WeatherIcons
        @ReadOnlyComposable
        @Composable
        get() = LocalWeatherIcons.current

    val images: WeatherImages
        @ReadOnlyComposable
        @Composable
        get() = LocalWeatherImages.current
}
