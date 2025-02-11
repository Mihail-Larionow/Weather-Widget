package com.michel.deeplinks.domain.models

sealed interface Screen : DeeplinkAction {
    data object Profile : Screen
    data object AppInfo : Screen
}
