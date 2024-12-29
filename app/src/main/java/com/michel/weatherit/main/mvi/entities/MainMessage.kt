package com.michel.weatherit.main.mvi.entities

sealed interface MainMessage {
    data object FinishSplash : MainMessage
}
