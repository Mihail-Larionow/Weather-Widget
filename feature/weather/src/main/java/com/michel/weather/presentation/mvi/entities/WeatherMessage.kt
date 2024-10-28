package com.michel.weather.presentation.mvi.entities

import com.michel.weather.domain.WeatherInitData

sealed interface WeatherMessage {
    data class WeatherDataLoaded(val weatherInitData: WeatherInitData) : WeatherMessage
    data object TextWasCopied : WeatherMessage
}