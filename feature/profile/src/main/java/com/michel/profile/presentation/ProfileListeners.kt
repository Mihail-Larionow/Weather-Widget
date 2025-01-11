package com.michel.profile.presentation

data class ProfileListeners(
    val onBackClick: () -> Unit,
    val onAppInfoClick: () -> Unit,
    val onSettingsClick: () -> Unit,
)

internal val MockProfileListeners = ProfileListeners(
    onBackClick = { },
    onAppInfoClick = { },
    onSettingsClick = { },
)
