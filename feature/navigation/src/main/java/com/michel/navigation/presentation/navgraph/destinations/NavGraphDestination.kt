package com.michel.navigation.presentation.navgraph.destinations

import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.navigation.NavGraphBuilder
import com.michel.feature.navigation.R
import com.michel.navigation.presentation.navgraph.nodes.feature.buildProfileGraph
import com.michel.navigation.presentation.navgraph.nodes.feature.buildWeatherGraph
import com.michel.navigation.presentation.navgraph.nodes.mainGraph

abstract class NavGraphDestination<ARGS : Parcelable?>(
    @IdRes override val id: Int,
    val startDestination: OnScreenDestination<ARGS>,
    val graphBuilder: (NavGraphBuilder) -> Unit,
) : NavigableDestination<ARGS> {
    data object Main : NavGraphDestination<NoNavArgsStub>(
        id = R.id.main_graph,
        startDestination = FragmentDestination.Weather,
        graphBuilder = NavGraphBuilder::mainGraph,
    )
    data object Weather : NavGraphDestination<NoNavArgsStub>(
        id = R.id.weather_feature_graph,
        startDestination = FragmentDestination.Weather,
        graphBuilder = NavGraphBuilder::buildWeatherGraph,
    )
    data object Profile : NavGraphDestination<NoNavArgsStub>(
        id = R.id.profile_feature_graph,
        startDestination = FragmentDestination.Profile,
        graphBuilder = NavGraphBuilder::buildProfileGraph,
    )
}
