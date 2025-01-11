package com.michel.weather.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class Toolbar(
    val title: String,
    val avatarInfo: AvatarInfo,
)
