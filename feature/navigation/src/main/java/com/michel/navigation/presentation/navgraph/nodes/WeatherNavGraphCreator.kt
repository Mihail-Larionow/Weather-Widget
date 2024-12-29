package com.michel.navigation.presentation.navgraph.nodes

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.createGraph
import com.michel.navigation.presentation.navgraph.destinations.NavGraphDestination

internal fun NavController.createWeatherNavGraph(): NavGraph = createGraph(
    id = NavGraphDestination.Main.id,
    startDestination = NavGraphDestination.Weather.id,
    builder = NavGraphDestination.Main.graphBuilder,
)
