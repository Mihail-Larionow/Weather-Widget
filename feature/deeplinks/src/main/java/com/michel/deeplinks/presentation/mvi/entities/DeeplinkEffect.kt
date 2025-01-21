package com.michel.deeplinks.presentation.mvi.entities

import com.michel.deeplinks.navigation.DeeplinkNavDirection

sealed interface DeeplinkEffect {
    data class Navigate(val direction: DeeplinkNavDirection) : DeeplinkEffect
}
