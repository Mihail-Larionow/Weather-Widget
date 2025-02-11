package com.michel.weather.domain.usecases

import com.michel.weather.domain.repositories.WeatherDataRepository
import com.michel.weather.domain.models.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherStateUseCase @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository,
) {

    operator fun invoke(): Flow<Weather> = with(weatherDataRepository) {
        getWeatherData()
    }
}
