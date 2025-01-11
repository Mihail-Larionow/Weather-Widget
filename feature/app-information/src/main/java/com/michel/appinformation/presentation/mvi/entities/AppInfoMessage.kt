package com.michel.appinformation.presentation.mvi.entities

import com.michel.appinformation.navigation.AppInfoNavDirection

sealed interface AppInfoMessage {
    data object Empty : AppInfoMessage
    data class Navigate(val direction: AppInfoNavDirection) : AppInfoMessage
}
