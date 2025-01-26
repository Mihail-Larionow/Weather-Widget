package com.michel.settings.presentation.mvi.entities

import com.michel.settings.domain.model.SettingsItem

sealed interface SettingsState {
    data object Loading : SettingsState
    data class Loaded(
        val items: List<SettingsItem> = emptyList(),
    ) : SettingsState
}
