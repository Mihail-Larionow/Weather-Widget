package com.michel.weatherit.main.presentation.mvi.entities

sealed interface MainState {
    data object Loading : MainState
}
