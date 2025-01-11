package com.michel.settings.presentation.mvi.entities

import com.michel.settings.domain.model.SettingsItem

sealed interface SettingsIntent {
    data object BackClicked : SettingsIntent
    data class ToggleStateUpdated(val toggle: SettingsItem.Toggle) : SettingsIntent
}
