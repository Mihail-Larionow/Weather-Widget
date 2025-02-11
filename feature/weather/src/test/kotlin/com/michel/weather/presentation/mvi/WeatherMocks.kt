package com.michel.weather.presentation.mvi

import com.michel.weather.domain.models.Weather
import com.michel.weather.domain.repositories.WeatherDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object WeatherMocks {

    var weatherData = Weather(emptyList())

    val temperatures = listOf(33)
    val fullyWeatherData = Weather(temperatures)

    val weatherDataRepository: WeatherDataRepository = object : WeatherDataRepository {

        override fun getWeatherData(): StateFlow<Weather> =
            MutableStateFlow(fullyWeatherData)

        override suspend fun refreshWeatherData(): Result<Unit> = Result.success(Unit)
    }
}
