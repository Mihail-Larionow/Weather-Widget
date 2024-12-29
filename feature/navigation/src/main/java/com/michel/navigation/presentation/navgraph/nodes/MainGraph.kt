package com.michel.navigation.presentation.navgraph.nodes

import androidx.navigation.NavGraphBuilder
import com.michel.navigation.presentation.extensions.navigation
import com.michel.navigation.presentation.navgraph.destinations.NavGraphDestination


internal fun NavGraphBuilder.mainGraph() {
    navigation(NavGraphDestination.Weather)
}
