package com.michel.api

import androidx.compose.runtime.Composable
import kotlin.reflect.KType

interface NavDestination<ROUTE : Any> {

    val routeSpec: RouteSpec<ROUTE>

    val typeMap: Map<KType, CustomNavType>
        get() = emptyMap()

    val navDestinationType: NavDestinationType
        get() = NavDestinationType.Screen

    @Composable
    fun Content(route: ROUTE)
}
