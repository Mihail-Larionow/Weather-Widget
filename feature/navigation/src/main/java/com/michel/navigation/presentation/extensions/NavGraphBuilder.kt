package com.michel.navigation.presentation.extensions

import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.navigation.NavArgument
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType.ParcelableType
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.get
import com.michel.utils.extensions.view.ARGS_KEY
import com.michel.navigation.presentation.navgraph.destinations.DialogDestination
import com.michel.navigation.presentation.navgraph.destinations.FragmentDestination
import com.michel.navigation.presentation.navgraph.destinations.NavGraphDestination

fun NavGraphBuilder.fragment(
    destination: FragmentDestination<*>,
) {
    addDestination(
        destination = FragmentNavigator.Destination(
            provider[FragmentNavigator::class]
        ).apply {
            setClassName(destination.fragmentType.java.name)
            setupIdAndArgs(destination.id)
        }
    )
}

fun NavGraphBuilder.dialog(
    destination: DialogDestination<*>,
) {
    addDestination(
        destination = DialogFragmentNavigator.Destination(
            provider[DialogFragmentNavigator::class]
        ).apply {
            setClassName(destination.fragmentType.java.name)
            setupIdAndArgs(destination.id)
        }
    )
}

fun NavGraphBuilder.navigation(
    destination: NavGraphDestination<*>,
) {
    destination(
        NavGraphBuilder(
            id = destination.id,
            provider = provider,
            startDestination = destination.startDestination.id,
        ).also(destination.graphBuilder)
    )
}

private fun NavDestination.setupIdAndArgs(@IdRes navId: Int) {
    id = navId
    addArgument(
        argumentName = com.michel.utils.extensions.view.ARGS_KEY,
        argument = NavArgument.Builder()
            .setType(ParcelableType(Parcelable::class.java))
            .setIsNullable(true)
            .build()
    )
}
