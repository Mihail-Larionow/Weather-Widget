package com.michel.weather.domain.usecases

import android.util.Log
import com.michel.weather.domain.repositories.WeatherDataRepository
import javax.inject.Inject

class ReloadWeatherDataUseCase @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository,
) {
    suspend operator fun invoke(): Result<Unit> {

        return weatherDataRepository.refreshWeatherData().onFailure {

        }
    }
}
