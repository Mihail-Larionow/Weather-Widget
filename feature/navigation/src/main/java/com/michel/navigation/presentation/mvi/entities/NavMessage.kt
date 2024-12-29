package com.michel.navigation.presentation.mvi.entities

import com.michel.navigation.presentation.navigation.MainNavDirection

sealed interface NavMessage {
    data object Empty : NavMessage
    data class Navigate(val direction: MainNavDirection) : NavMessage
}
