package com.michel.profile.navigation

sealed interface ProfileNavDirection {
    data object Up : ProfileNavDirection
}
