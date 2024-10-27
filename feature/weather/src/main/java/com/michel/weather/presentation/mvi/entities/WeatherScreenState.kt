package com.michel.weather.presentation.mvi.entities

import androidx.compose.runtime.Immutable

@Immutable
data class WeatherScreenState(
    val weatherInfo: String = "none",
)