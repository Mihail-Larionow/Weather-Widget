package com.michel.appinformation.navigation

sealed interface AppInfoNavDirection {
    data object Up : AppInfoNavDirection
}
