package com.michel.profile.presentation.mvi.entities

sealed interface ProfileState {
    data object Loading : ProfileState
    data class Loaded(val name: String) : ProfileState
}
