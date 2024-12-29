package com.michel.navigation.presentation.mvi.entities

import com.michel.navigation.presentation.navigation.MainNavDirection

sealed interface NavEffect {
    data class Navigate(val direction: MainNavDirection) : NavEffect
}
