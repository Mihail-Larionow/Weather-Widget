package com.michel.weather.data.datasources

import com.michel.weather.domain.models.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherLocalDataSource @Inject constructor() {

    val weatherDataState = MutableStateFlow(Weather(listOf(39)))
}
