package com.michel.navigation.presentation.navcontroller

import androidx.navigation.NavController

interface NavControllerFragment {

    fun getNavController(): NavController?

    fun navigateUp()
}
