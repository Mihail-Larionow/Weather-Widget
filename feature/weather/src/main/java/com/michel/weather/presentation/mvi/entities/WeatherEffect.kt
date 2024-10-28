package com.michel.weather.presentation.mvi.entities

sealed interface WeatherEffect {
    data object ShowCopySnackbar : WeatherEffect
}