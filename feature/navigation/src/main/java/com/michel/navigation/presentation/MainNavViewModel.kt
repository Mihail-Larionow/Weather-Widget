package com.michel.navigation.presentation

import androidx.lifecycle.ViewModel
import com.michel.api.NavRoute
import com.michel.impl.NavViewModel
import com.michel.impl.NavMessage
import com.michel.navigation.domain.MainNavController
import com.michel.weather.navigation.WeatherRoute
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MainNavViewModel @Inject constructor(
    private val navController: MainNavController,
) : ViewModel(), NavViewModel<NavRoute> {

    override val startDestinationRoute: NavRoute
        get() = WeatherRoute

    override val navMessages: Flow<NavMessage<out NavRoute>>
        get() = navController.trickle.toFlow()
}
