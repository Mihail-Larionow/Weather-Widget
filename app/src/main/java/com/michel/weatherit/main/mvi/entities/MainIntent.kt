package com.michel.weatherit.main.mvi.entities

sealed interface MainIntent {
    data object Stop : MainIntent
}
