package com.michel.weather.presentation.mvi

import com.michel.weather.domain.models.WeatherDomainModel
import com.michel.weather.domain.repositories.WeatherDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object WeatherMocks {

    var weatherData = WeatherDomainModel("")

    val fullyWeatherData = WeatherDomainModel("33")

    val weatherDataRepository: WeatherDataRepository = object : WeatherDataRepository {

        override fun getWeatherData(): StateFlow<WeatherDomainModel> =
            MutableStateFlow(fullyWeatherData)

        override suspend fun refreshWeatherData(): Result<Unit> = Result.success(Unit)
    }
}
