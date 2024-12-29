package com.michel.weather.presentation

data class WeatherListeners(
    val onProfileClick: () -> Unit,
    val onSettingsClick: () -> Unit,
)

internal val MockWeatherListeners = WeatherListeners(
    onProfileClick = { },
    onSettingsClick = { },
)
