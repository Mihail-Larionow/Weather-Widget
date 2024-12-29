package com.michel.weather.domain

import com.michel.models.WeatherData
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository,
) {

    suspend operator fun invoke(): WeatherData = weatherDataRepository.getWeatherData()
}
