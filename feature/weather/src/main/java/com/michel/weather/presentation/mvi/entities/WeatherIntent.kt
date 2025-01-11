package com.michel.weather.presentation.mvi.entities

sealed interface WeatherIntent {
    data object Reload : WeatherIntent
    data object ProfileClicked : WeatherIntent
    data object SettingsClicked : WeatherIntent
}
