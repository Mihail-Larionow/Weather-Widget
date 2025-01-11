package com.michel.profile.presentation.mvi.entities

sealed interface ProfileIntent {
    data object BackClicked : ProfileIntent
    data object AppInfoClicked : ProfileIntent
    data object SettingsClicked : ProfileIntent
}
