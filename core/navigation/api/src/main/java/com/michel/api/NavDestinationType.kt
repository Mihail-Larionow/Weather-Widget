package com.michel.api

sealed interface NavDestinationType {
    data object Screen : NavDestinationType
}
