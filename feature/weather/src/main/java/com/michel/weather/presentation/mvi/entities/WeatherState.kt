package com.michel.weather.presentation.mvi.entities

import androidx.compose.runtime.Immutable

sealed interface WeatherState {
    data object Loading : WeatherState

    @Immutable
    data class Loaded(
        val temperature: List<Int> = emptyList(),
    ) : WeatherState
}

