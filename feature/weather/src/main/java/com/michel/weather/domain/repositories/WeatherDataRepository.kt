package com.michel.weather.domain.repositories

import com.michel.weather.domain.models.WeatherDomainModel
import kotlinx.coroutines.flow.StateFlow

interface WeatherDataRepository {

    fun getWeatherData(): StateFlow<WeatherDomainModel>

    suspend fun refreshWeatherData() : Result<Unit>
}
