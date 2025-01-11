package com.michel.navigation.domain

import com.michel.api.NavRoute
import com.michel.impl.NavMessage
import com.michel.navigation.di.NavScope
import com.michel.utils.coroutines.trickle.MutableTrickle
import com.michel.utils.coroutines.trickle.Trickle
import javax.inject.Inject

@NavScope
internal class MainNavController @Inject constructor() {

    private val _trickle = MutableTrickle<NavMessage<out NavRoute>>()

    val trickle: Trickle<NavMessage<out NavRoute>>
        get() = _trickle

    fun navigate(route: NavRoute) {
        _trickle.put(NavMessage.Navigate(route))
    }

    fun navigateUp() {
        _trickle.put(NavMessage.Up)
    }
}
