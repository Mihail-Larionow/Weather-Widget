package com.michel.settings.presentation.mvi.entities

import com.michel.settings.navigation.SettingsNavDirection

sealed interface SettingsEffect {
    data class Navigate(val direction: SettingsNavDirection) : SettingsEffect
}
