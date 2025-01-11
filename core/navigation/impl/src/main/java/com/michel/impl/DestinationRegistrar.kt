package com.michel.impl

import androidx.navigation.NavGraphBuilder
import com.michel.api.NavDestinationType
import com.michel.api.DestinationsDataSource
import com.michel.api.NavDestination

fun interface DestinationRegistrar {
    fun NavGraphBuilder.registerDestinations()
}

fun DestinationRegistrar(dataSource: DestinationsDataSource<*>): DestinationRegistrar =
    DestinationRegistrarImpl(dataSource, DestinationBuilderImpl())

private class DestinationRegistrarImpl(
    private val dataSource: DestinationsDataSource<*>,
    private val destinationBuilder: DestinationBuilder,
) : DestinationRegistrar {

    override fun NavGraphBuilder.registerDestinations() {
        dataSource().forEach { destination ->
            register(destination)
        }
    }

    private fun <R : Any> NavGraphBuilder.register(
        destination: NavDestination<R>,
    ) {
        destination(
            when (destination.navDestinationType) {
                is NavDestinationType.Screen -> with(destinationBuilder) {
                    composeDestinationBuilder(destination)
                }
            }
        )
    }
}
