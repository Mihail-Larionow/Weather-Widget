package com.michel.weather.presentation.mvi.entities

import com.michel.weather.domain.models.Weather
import com.michel.weather.navigation.WeatherNavDirection

sealed interface WeatherMessage {
    data object Empty : WeatherMessage
    data class WeatherDataLoaded(val weatherData: Weather) : WeatherMessage
    data class Navigate(val direction: WeatherNavDirection) : WeatherMessage
    data class ShowInfoSnackbar(val text: String) : WeatherMessage
}
