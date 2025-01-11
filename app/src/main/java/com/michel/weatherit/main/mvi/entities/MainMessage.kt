package com.michel.weatherit.main.mvi.entities

sealed interface MainMessage {
    data object Empty : MainMessage
    data object FinishSplash : MainMessage
}
