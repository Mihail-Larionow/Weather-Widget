package com.michel.weather.presentation.mvi.entities

import com.michel.weather.domain.WeatherInitData
import com.michel.weather.navigation.WeatherNavDirection

sealed interface WeatherMessage {
    data object Empty : WeatherMessage
    data class WeatherDataLoaded(val weatherInitData: WeatherInitData) : WeatherMessage
    data class Navigate(val direction: WeatherNavDirection) : WeatherMessage
}
