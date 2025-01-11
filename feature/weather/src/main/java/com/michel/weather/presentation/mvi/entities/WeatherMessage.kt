package com.michel.weather.presentation.mvi.entities

import com.michel.weather.domain.models.WeatherDomainModel
import com.michel.weather.navigation.WeatherNavDirection

sealed interface WeatherMessage {
    data object Empty : WeatherMessage
    data class WeatherDataLoaded(val weatherData: WeatherDomainModel) : WeatherMessage
    data class Navigate(val direction: WeatherNavDirection) : WeatherMessage
}
