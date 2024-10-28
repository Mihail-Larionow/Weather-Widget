package com.michel.weather.presentation.mvi.entities

import androidx.compose.runtime.Immutable

@Immutable
data class WeatherState(
    val weatherInfo: String = "none",
)