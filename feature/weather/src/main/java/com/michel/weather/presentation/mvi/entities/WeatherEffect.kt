package com.michel.weather.presentation.mvi.entities

import com.michel.weather.navigation.WeatherNavDirection

sealed interface WeatherEffect {
    data class Navigate(val direction: WeatherNavDirection) : WeatherEffect
    data class ShowInfoSnackbar(val text: String) : WeatherEffect
}
