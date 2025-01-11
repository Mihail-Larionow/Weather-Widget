package com.michel.api

import androidx.compose.runtime.Composable

interface NavDestination<R : Any> {

    val routeSpec: RouteSpec<R>

    val navDestinationType: NavDestinationType
        get() = NavDestinationType.Screen

    @Composable
    fun Content(route: R)
}
