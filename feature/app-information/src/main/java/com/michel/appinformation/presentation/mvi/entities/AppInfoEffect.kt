package com.michel.appinformation.presentation.mvi.entities

import com.michel.appinformation.navigation.AppInfoNavDirection

sealed interface AppInfoEffect {
    data class Navigate(val direction: AppInfoNavDirection) : AppInfoEffect
}
