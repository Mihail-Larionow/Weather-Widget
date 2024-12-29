package com.michel.navigation.presentation.navgraph.nodes.feature

import androidx.navigation.NavGraphBuilder
import com.michel.navigation.presentation.extensions.fragment
import com.michel.navigation.presentation.extensions.navigation
import com.michel.navigation.presentation.navgraph.destinations.FragmentDestination
import com.michel.navigation.presentation.navgraph.destinations.NavGraphDestination

fun NavGraphBuilder.buildWeatherGraph() {
    fragment(FragmentDestination.Weather)
    navigation(NavGraphDestination.Profile)
}
