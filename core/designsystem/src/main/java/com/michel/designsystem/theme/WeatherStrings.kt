package com.michel.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.michel.designsystem.R.string as stringsR

class WeatherStrings internal constructor() {

    val weather = FeatureWeather()

    val settings = FeatureSettings()
}

class FeatureWeather internal constructor() {

    val toolbarTitle: String
        @Composable
        get() = stringResource(id = stringsR.feature_weather_toolbar_title)
}

class FeatureSettings internal constructor() {

    val toolbarTitle: String
        @Composable
        get() = stringResource(id = stringsR.feature_settings_toolbar_title)

    val themeToggleTitle: String
        @Composable
        get() = stringResource(id = stringsR.feature_settings_theme_toggle_title)

    val themeToggleSubtitle: String
        @Composable
        get() = stringResource(id = stringsR.feature_settings_theme_toggle_subtitle)
}
