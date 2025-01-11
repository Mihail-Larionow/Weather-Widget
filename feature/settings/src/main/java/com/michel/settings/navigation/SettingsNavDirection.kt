package com.michel.settings.navigation

sealed interface SettingsNavDirection {
    data object Up : SettingsNavDirection
}
