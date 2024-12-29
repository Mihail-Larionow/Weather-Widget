package com.michel.navigation.presentation.navcontroller

import android.os.Parcelable
import com.michel.navigation.presentation.navgraph.destinations.NavigableDestination
import com.michel.navigation.presentation.navgraph.destinations.NoNavArgsStub

interface WeatherNavController {
    val previousDestinationId: Int?

    fun <ARGS : Parcelable?, DEST : NavigableDestination<ARGS>> navigate(
        destination: DEST,
        args: ARGS,
        options: WeatherNavOptions = WeatherNavOptions(),
        extras: Map<String, Any>? = null,
    )

    fun navigateUp()

    fun finish()

    /**
     * @return true if the stack was popped at least once and the user has been navigated to another destination, false otherwise
     * false possible if destination is not found in stack
     */
    fun popUpTo(popUpOptions: WeatherNavOptions.PopUp): Boolean
}

fun <DEST : NavigableDestination<NoNavArgsStub>> WeatherNavController.navigate(
    destination: DEST,
    options: WeatherNavOptions = WeatherNavOptions(),
    extras: Map<String, Any>? = null,
) {
    navigate(destination = destination, args = NoNavArgsStub, options = options, extras = extras)
}
