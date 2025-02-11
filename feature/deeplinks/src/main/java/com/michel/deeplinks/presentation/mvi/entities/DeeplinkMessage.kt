package com.michel.deeplinks.presentation.mvi.entities

import com.michel.deeplinks.navigation.DeeplinkNavDirection

sealed interface DeeplinkMessage {
    data object Empty : DeeplinkMessage
    data class Navigate(val direction: DeeplinkNavDirection) : DeeplinkMessage
}
