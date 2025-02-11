package com.michel.profile.presentation.model

sealed interface ProfileHeader {
    data object Authorized : ProfileHeader
    data object UnAuthorized : ProfileHeader
}
