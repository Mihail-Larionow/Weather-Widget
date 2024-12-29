package com.michel.navigation.presentation.navcontroller

import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import com.michel.navigation.presentation.extensions.navigate
import com.michel.navigation.presentation.navgraph.destinations.NavigableDestination
import com.michel.ui.activity.ActivityRegistry
import com.michel.ui.activity.getOfType
import javax.inject.Inject

internal class WeatherNavControllerImpl @Inject constructor(
    private val activityRegistry: ActivityRegistry,
) : WeatherNavController {

    private val navControllerFragment: NavControllerFragment?
        get() = activityRegistry.getOfType<NavControllerActivity>()
            ?.getNavControllerFragment()

    override val previousDestinationId: Int?
        get() = navControllerFragment
            ?.getNavController()
            ?.previousBackStackEntry
            ?.destination
            ?.id

    override fun <ARGS : Parcelable?, DEST : NavigableDestination<ARGS>> navigate(
        destination: DEST,
        args: ARGS,
        options: WeatherNavOptions,
        extras: Map<String, Any>?,
    ) {
        executeOrReportOnCatch(
            functionName = "navigate",
        ) { navController ->
            Log.d("Navigation", "Navigate to $destination")
            navController.navigate(
                destination = destination,
                args = args,
                navOptions = options.toNavOptions(defaults = null),
                navigatorExtras = extras?.toFragmentNavigatorExtras(),
            )
        }
    }

    override fun navigateUp() {
        executeOrReportOnCatch(
            functionName = "navigateUp"
        ) { navController ->
            navController.navigateUp()
            Log.d("Navigation", "UP")
        }
    }

    override fun finish() {
        activityRegistry.getOfType<NavControllerActivity>()?.finish()
    }

    override fun popUpTo(popUpOptions: WeatherNavOptions.PopUp): Boolean {
        var result = false
        executeOrReportOnCatch(
            functionName = "popUpTo"
        ) { navController ->
            result = navController.popBackStack(
                destinationId = popUpOptions.destinationId,
                inclusive = popUpOptions.inclusive,
                saveState = popUpOptions.saveState,
            )
        }
        return result
    }

    private fun executeOrReportOnCatch(
        functionName: String,
        action: (navController: NavController) -> Unit,
    ) {
        try {
            val navControllerFragment = activityRegistry.getOfType<NavControllerActivity>()
                ?.getNavControllerFragment()
                ?: throw NullPointerException("NavControllerFragment is null")

            val travelNavController = navControllerFragment.getNavController()
                ?: throw NullPointerException("NavController is null")

            action(travelNavController)
        } catch (exception: Exception) {
            Log.e("Navigation", "TravelNavControllerImpl $functionName error")
        }
    }

    private fun WeatherNavOptions.toNavOptions(defaults: NavOptions?) = NavOptions.Builder()
        .setLaunchSingleTop(singleTop ?: defaults?.shouldLaunchSingleTop() ?: false)
        .setRestoreState(restoreState ?: defaults?.shouldRestoreState() ?: false)
        .setPopUpTo(popUpOptions, defaults)
        .setAnimations(animations, defaults)
        .build()

    private fun NavOptions.Builder.setPopUpTo(
        popUpOptions: WeatherNavOptions.PopUp?,
        defaults: NavOptions?
    ) =
        popUpOptions?.let {
            setPopUpTo(
                destinationId = popUpOptions.destinationId,
                inclusive = popUpOptions.inclusive,
                saveState = popUpOptions.saveState
            )
        } ?: defaults?.let {
            setPopUpTo(
                destinationId = defaults.popUpToId,
                inclusive = defaults.isPopUpToInclusive(),
                saveState = defaults.shouldPopUpToSaveState()
            )
        } ?: this

    private fun NavOptions.Builder.setAnimations(
        animations: WeatherNavOptions.Animations?,
        defaults: NavOptions?
    ) =
        animations?.let {
            setEnterAnim(animations.enter)
            setExitAnim(animations.exit)
            setPopEnterAnim(animations.popEnter)
            setPopExitAnim(animations.popExit)
        } ?: defaults?.let {
            setEnterAnim(defaults.enterAnim)
            setExitAnim(defaults.exitAnim)
            setPopEnterAnim(defaults.popEnterAnim)
            setPopExitAnim(defaults.popExitAnim)
        } ?: this

    private fun Map<String, Any>.toFragmentNavigatorExtras(): Navigator.Extras {
        val builder = FragmentNavigator.Extras.Builder()
        for ((key, value) in this) {
            if (value is View) {
                builder.addSharedElement(value, key)
            }
        }
        return builder.build()
    }
}
