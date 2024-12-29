package com.michel.weather.navigation

sealed interface WeatherNavDirection {
    data object Up : WeatherNavDirection
    data object ToProfile : WeatherNavDirection
    data object ToSettings : WeatherNavDirection
}
