package com.michel.settings.domain.model

sealed interface SettingsItem {
    data class Toggle(val toggleType: ToggleType, val isEnabled: Boolean) : SettingsItem
}

enum class ToggleType {
    THEME,
}
