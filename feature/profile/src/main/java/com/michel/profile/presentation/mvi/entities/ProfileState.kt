package com.michel.profile.presentation.mvi.entities

import com.michel.profile.presentation.model.ProfileHeader

sealed interface ProfileState {
    data object Loading : ProfileState

    data class Loaded(
        val header: ProfileHeader,
    ) : ProfileState
}
