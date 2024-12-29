package com.michel.weatherit.main.mvi.entities

sealed interface MainEffect {
    data object FinishSplash : MainEffect
}
