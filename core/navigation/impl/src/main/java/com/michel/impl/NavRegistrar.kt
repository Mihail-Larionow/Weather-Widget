package com.michel.impl

import androidx.navigation.NavGraphBuilder
import com.michel.api.NavDestinationType
import com.michel.api.DestinationsDataSource
import com.michel.api.NavDestination

fun interface Registrar {
    fun NavGraphBuilder.registerDestinations()
}

fun Registrar(dataSource: DestinationsDataSource<*>): Registrar =
    RegistrarImpl(dataSource, DestinationBuilderImpl())

private class RegistrarImpl(
    private val dataSource: DestinationsDataSource<*>,
    private val destinationBuilder: DestinationBuilder,
) : Registrar {

    override fun NavGraphBuilder.registerDestinations() {
        dataSource().forEach { destination ->
            register(destination)
        }
    }

    private fun <ROUTE : Any> NavGraphBuilder.register(
        destination: NavDestination<ROUTE>,
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
