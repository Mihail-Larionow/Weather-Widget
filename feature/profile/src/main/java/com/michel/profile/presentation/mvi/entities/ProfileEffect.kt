package com.michel.profile.presentation.mvi.entities

import com.michel.profile.navigation.ProfileNavDirection

sealed interface ProfileEffect {
    data class Navigate(val direction: ProfileNavDirection) : ProfileEffect
}
