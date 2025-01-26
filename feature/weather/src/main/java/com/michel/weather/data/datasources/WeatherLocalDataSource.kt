package com.michel.weather.data.datasources

import com.michel.weather.domain.models.WeatherDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherLocalDataSource @Inject constructor() {

    val weatherDataState = MutableStateFlow(WeatherDomainModel(listOf(39.0)))
}
