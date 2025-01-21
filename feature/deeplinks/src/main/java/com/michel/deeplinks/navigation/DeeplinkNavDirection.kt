package com.michel.deeplinks.navigation

sealed interface DeeplinkNavDirection {
    data object ToProfile : DeeplinkNavDirection
    data object ToAppInfo : DeeplinkNavDirection
}
