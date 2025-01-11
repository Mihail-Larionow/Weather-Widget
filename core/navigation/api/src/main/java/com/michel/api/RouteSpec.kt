package com.michel.api

import kotlin.reflect.KClass

@JvmInline
value class RouteSpec<ROUTE : Any>(val kClass: KClass<ROUTE>)

inline fun <reified ROUTE : Any> routeSpec(): RouteSpec<ROUTE> = RouteSpec(ROUTE::class)
