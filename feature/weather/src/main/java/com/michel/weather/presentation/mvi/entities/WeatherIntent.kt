package com.michel.weather.presentation.mvi.entities

sealed interface WeatherIntent {
    data object CopyActionClicked : WeatherIntent
}