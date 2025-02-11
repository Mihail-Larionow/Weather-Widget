package com.michel.weatherit.main.presentation.mvi.entities

sealed interface MainEffect {
    data object FinishSplash : MainEffect
}
