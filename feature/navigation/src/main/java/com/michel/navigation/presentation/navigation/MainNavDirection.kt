package com.michel.navigation.presentation.navigation

sealed interface MainNavDirection {
    data object Up : MainNavDirection
    data object ToWeather : MainNavDirection
}
