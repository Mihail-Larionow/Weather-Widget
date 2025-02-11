package com.michel.weather.presentation.mvi.entities

import com.michel.weather.presentation.models.SnackbarAction

sealed interface WeatherIntent {
    data object Reload : WeatherIntent
    data object ProfileClicked : WeatherIntent
    data object SettingsClicked : WeatherIntent
    data class SnackbarButtonClicked(val action: SnackbarAction) : WeatherIntent
}
