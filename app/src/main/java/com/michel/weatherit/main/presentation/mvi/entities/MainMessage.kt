package com.michel.weatherit.main.presentation.mvi.entities

sealed interface MainMessage {
    data object Empty : MainMessage
    data object FinishSplash : MainMessage
}
