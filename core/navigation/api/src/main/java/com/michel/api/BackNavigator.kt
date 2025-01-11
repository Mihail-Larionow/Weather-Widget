package com.michel.api

interface BackNavigator {

    fun popBackStack(): Boolean

    fun navigateUp(): Boolean
}
