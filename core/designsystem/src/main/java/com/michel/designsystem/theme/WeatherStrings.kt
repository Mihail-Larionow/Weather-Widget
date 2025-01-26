package com.michel.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.michel.designsystem.R.string as stringsR

class WeatherStrings internal constructor() {

    val weather = FeatureWeather()

    val settings = FeatureSettings()

    val profile = FeatureProfile()
}

class FeatureWeather internal constructor() {

    val toolbarTitle: String
        @Composable
        get() = stringResource(id = stringsR.common_strings_now)

    val celsiusDegree: String
        @Composable
        get() = stringResource(id = stringsR.feature_weather_strings_celsius_degree)
}

class FeatureSettings internal constructor() {

    val toolbarTitle: String
        @Composable
        get() = stringResource(id = stringsR.common_strings_settings)
}

class FeatureProfile internal constructor() {

    val settingsMenuItemTitle: String
        @Composable
        get() = stringResource(id = stringsR.common_strings_settings)

    val appInfoMenuItemTitle: String
        @Composable
        get() = stringResource(id = stringsR.common_strings_app_info)
}
