package com.michel.weatherit.main.mvi.entities

sealed interface MainState {
    data object Loading : MainState
}
