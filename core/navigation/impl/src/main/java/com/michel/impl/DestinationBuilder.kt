package com.michel.impl

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestinationBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.ComposeNavigatorDestinationBuilder
import androidx.navigation.get
import androidx.navigation.serialization.decodeArguments
import com.michel.api.NavDestination
import com.michel.api.RouteSpec
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

interface DestinationBuilder {
    fun <R : Any> NavGraphBuilder.composeDestinationBuilder(
        destination: NavDestination<R>,
    ): NavDestinationBuilder<ComposeNavigator.Destination>
}

internal class DestinationBuilderImpl : DestinationBuilder {

    override fun <R : Any> NavGraphBuilder.composeDestinationBuilder(destination: NavDestination<R>) =
        ComposeNavigatorDestinationBuilder(
            navigator = provider[ComposeNavigator::class],
            route = destination.routeSpec.kClass,
            typeMap = emptyMap(),
        ) { navBackStackEntry ->
            val route = navBackStackEntry.toRoute(destination.routeSpec)
            CompositionLocalProvider(
                LocalAnimatedContentScope provides this@ComposeNavigatorDestinationBuilder,
            ) {
                destination.Content(route)
            }
        }

    @OptIn(ExperimentalSerializationApi::class)
    @SuppressLint("RestrictedApi")
    private fun <T : Any> NavBackStackEntry.toRoute(routeSpec: RouteSpec<T>): T {
        val bundle = arguments ?: Bundle()
        val typeMap = destination.arguments.mapValues { it.value.type }
        val serializer = serializer(routeSpec.kClass, emptyList(), false) as KSerializer<T>
        return serializer.decodeArguments(bundle, typeMap)
    }
}

val LocalAnimatedContentScope = staticCompositionLocalOf<AnimatedContentScope> {
    error("CompositionLocal AnimatedContentScope not present")
}

