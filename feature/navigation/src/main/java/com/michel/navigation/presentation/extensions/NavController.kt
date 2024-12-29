package com.michel.navigation.presentation.extensions

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.michel.navigation.presentation.navgraph.destinations.NavigableDestination
import com.michel.navigation.presentation.navgraph.destinations.NoNavArgsStub
import com.michel.utils.extensions.view.ARGS_KEY

internal fun <ARGS : Parcelable?, DEST : NavigableDestination<ARGS>> NavController.navigate(
    destination: DEST,
    args: ARGS,
    navOptions: NavOptions?,
    navigatorExtras: Navigator.Extras?,
) {
    navigate(
        destination = destination.id,
        args = args,
        navOptions = navOptions,
        navigatorExtras = navigatorExtras
    )
}

private fun NavController.navigate(
    @IdRes destination: Int,
    args: Parcelable?,
    navOptions: NavOptions?,
    navigatorExtras: Navigator.Extras?,
) {
    val bundle = if (args != null && args !is NoNavArgsStub) {
        Bundle().apply { putParcelable(ARGS_KEY, args) }
    } else {
        null
    }
    navigate(
        resId = destination,
        args = bundle,
        navOptions = navOptions,
        navigatorExtras = navigatorExtras,
    )
}
