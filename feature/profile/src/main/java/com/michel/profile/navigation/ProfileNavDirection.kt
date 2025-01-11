package com.michel.profile.navigation

sealed interface ProfileNavDirection {
    data object Up : ProfileNavDirection
    data object AppInfo : ProfileNavDirection
    data object Settings : ProfileNavDirection
}
