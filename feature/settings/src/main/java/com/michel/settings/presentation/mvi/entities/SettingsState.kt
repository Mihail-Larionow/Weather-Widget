package com.michel.settings.presentation.mvi.entities

import com.michel.settings.domain.model.SettingsItem

data class SettingsState(
    val items: List<SettingsItem> = emptyList(),
)
