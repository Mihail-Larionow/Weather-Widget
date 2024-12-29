package com.michel.weather.di

import kotlinx.coroutines.CoroutineScope

internal interface WeatherInternalApi {

    val coroutineScope: CoroutineScope
}
