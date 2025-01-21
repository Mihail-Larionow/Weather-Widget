package com.michel.deeplinks.presentation.mvi.entities

sealed interface DeeplinkState {
    data object Handling : DeeplinkState
}
