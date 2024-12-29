package com.michel.navigation.presentation.navgraph.nodes.feature

import androidx.navigation.NavGraphBuilder
import com.michel.navigation.presentation.extensions.fragment
import com.michel.navigation.presentation.navgraph.destinations.FragmentDestination

fun NavGraphBuilder.buildProfileGraph() {
    fragment(FragmentDestination.Profile)
}
