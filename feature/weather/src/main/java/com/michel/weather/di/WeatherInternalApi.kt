package com.michel.weather.di

import com.michel.api.FeatureNavApi
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.weather.navigation.WeatherNavDirection
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState

internal interface WeatherInternalApi {

    val viewModelFactory: StoreViewModelFactory<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage>

    val navApi: FeatureNavApi<WeatherNavDirection>
}
