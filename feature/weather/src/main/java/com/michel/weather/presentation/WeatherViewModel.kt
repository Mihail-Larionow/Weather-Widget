package com.michel.weather.presentation

import com.michel.mvi.BaseViewModel
import com.michel.weather.presentation.mvi.entities.WeatherScreenEffect
import com.michel.weather.presentation.mvi.entities.WeatherScreenIntent
import com.michel.weather.presentation.mvi.entities.WeatherScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
internal class WeatherViewModel @Inject constructor(

) : BaseViewModel<WeatherScreenIntent, WeatherScreenEffect, WeatherScreenState, WeatherScreenState>() {

    override val state: StateFlow<WeatherScreenState> = MutableStateFlow(WeatherScreenState())

    override val effect: Flow<WeatherScreenEffect> = flow { }

    override fun handleIntent(intent: WeatherScreenIntent) {
        when (intent) {

            else -> {}
        }
    }

}