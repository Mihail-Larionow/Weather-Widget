package com.michel.weather.domain.repositories

import com.michel.weather.domain.models.Weather
import kotlinx.coroutines.flow.StateFlow

interface WeatherDataRepository {

    fun getWeatherData(): StateFlow<Weather>

    suspend fun refreshWeatherData() : Result<Unit>
}
