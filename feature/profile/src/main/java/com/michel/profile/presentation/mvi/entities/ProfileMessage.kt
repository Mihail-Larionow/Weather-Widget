package com.michel.profile.presentation.mvi.entities

import com.michel.profile.navigation.ProfileNavDirection

sealed interface ProfileMessage {
    data object Empty : ProfileMessage
    data class Navigate(val direction: ProfileNavDirection) : ProfileMessage
}
