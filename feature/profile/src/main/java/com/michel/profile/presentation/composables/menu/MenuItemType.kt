package com.michel.profile.presentation.composables.menu

import androidx.compose.runtime.Composable
import com.michel.designsystem.theme.WeatherTheme

enum class MenuItemType {
    SETTINGS,
    APP_INFO;
}

@Composable
internal fun MenuItemType.getTitle(): String = when (this) {
    MenuItemType.SETTINGS -> WeatherTheme.strings.profile.settingsMenuItemTitle
    MenuItemType.APP_INFO -> WeatherTheme.strings.profile.appInfoMenuItemTitle
}
