package com.michel.impl

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestinationBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.ComposeNavigatorDestinationBuilder
import androidx.navigation.get
import androidx.navigation.serialization.decodeArguments
import com.michel.api.CustomNavType
import com.michel.api.NavDestination
import com.michel.api.RouteSpec
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import kotlin.reflect.KType

interface DestinationBuilder {
    fun <ROUTE : Any> NavGraphBuilder.composeDestinationBuilder(
        destination: NavDestination<ROUTE>,
    ): NavDestinationBuilder<ComposeNavigator.Destination>
}

internal class DestinationBuilderImpl : DestinationBuilder {

    override fun <ROUTE : Any> NavGraphBuilder.composeDestinationBuilder(destination: NavDestination<ROUTE>) =
        ComposeNavigatorDestinationBuilder(
            provider[ComposeNavigator::class],
            destination.routeSpec.kClass,
            destination.typeMap.toNavTypes(),
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

    private fun Map<KType, CustomNavType>.toNavTypes(): Map<KType, @JvmSuppressWildcards NavType<*>> {
        return mapValues { entry ->
            when (entry.value) {
                CustomNavType.PARCELABLE -> parcelableType(entry.key)
            }
        }
    }
}

val LocalAnimatedContentScope = staticCompositionLocalOf<AnimatedContentScope> {
    error("CompositionLocal AnimatedContentScope not present")
}

