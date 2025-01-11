package com.michel.settings.presentation.mvi.entities

import com.michel.settings.domain.model.SettingsItem
import com.michel.settings.navigation.SettingsNavDirection

sealed interface SettingsMessage {
    data object Empty : SettingsMessage
    data class Navigate(val direction: SettingsNavDirection) : SettingsMessage
    data class UpdateSettingsList(val items: List<SettingsItem>) : SettingsMessage
}
