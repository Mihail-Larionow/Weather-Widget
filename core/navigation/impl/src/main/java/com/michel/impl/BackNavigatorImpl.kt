package com.michel.impl

import androidx.navigation.NavController
import com.michel.api.BackNavigator

internal class BackNavigatorImpl(private val navController: NavController) : BackNavigator {

    override fun navigateUp(): Boolean = navController.navigateUp()

    override fun popBackStack(): Boolean = navController.popBackStack()
}
