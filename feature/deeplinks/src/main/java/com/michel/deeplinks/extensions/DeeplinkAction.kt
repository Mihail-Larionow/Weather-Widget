package com.michel.deeplinks.extensions

import com.michel.deeplinks.domain.models.DeeplinkAction
import com.michel.deeplinks.domain.models.Screen
import com.michel.deeplinks.navigation.DeeplinkNavDirection
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkMessage

internal fun DeeplinkAction.toDeeplinkMessage() : DeeplinkMessage = when(this) {
    is Screen.AppInfo -> DeeplinkMessage.Navigate(DeeplinkNavDirection.ToAppInfo)
    is Screen.Profile ->  DeeplinkMessage.Navigate(DeeplinkNavDirection.ToProfile)
}
