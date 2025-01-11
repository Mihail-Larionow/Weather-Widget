package com.michel.appinformation.presentation.mvi.entities

import com.michel.appinformation.navigation.AppInfoNavDirection

sealed interface AppInfoMessage {
    data class Navigate(val direction: AppInfoNavDirection) : AppInfoMessage
}
