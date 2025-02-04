package com.michel.weather.presentation.mvi.entities

import com.michel.weather.navigation.WeatherNavDirection
import com.michel.weather.presentation.models.SnackbarData

sealed interface WeatherEffect {
    data class ShowErrorSnackbar(val info: SnackbarData) : WeatherEffect
    data class Navigate(val direction: WeatherNavDirection) : WeatherEffect
}
