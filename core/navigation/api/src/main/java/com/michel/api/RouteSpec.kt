package com.michel.api

import kotlin.reflect.KClass

@JvmInline
value class RouteSpec<R : Any>(val kClass: KClass<R>)

inline fun <reified R : Any> routeSpec(): RouteSpec<R> = RouteSpec(R::class)
