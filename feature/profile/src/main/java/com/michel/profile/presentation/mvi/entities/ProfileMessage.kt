package com.michel.profile.presentation.mvi.entities

import com.michel.profile.navigation.ProfileNavDirection

sealed interface ProfileMessage {
    data class Navigate(val direction: ProfileNavDirection) : ProfileMessage
}
