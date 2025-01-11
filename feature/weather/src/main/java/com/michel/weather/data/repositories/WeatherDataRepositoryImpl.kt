package com.michel.weather.data.repositories

import com.michel.weather.data.datasources.WeatherLocalDataSource
import com.michel.weather.data.datasources.WeatherNetworkDataSource
import com.michel.weather.data.extensions.toWeatherDomainModel
import com.michel.weather.domain.models.WeatherDomainModel
import com.michel.weather.domain.repositories.WeatherDataRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class WeatherDataRepositoryImpl @Inject constructor(
    private val networkDataSource: WeatherNetworkDataSource,
    private val localDataSource: WeatherLocalDataSource,
) : WeatherDataRepository {

    override fun getWeatherData(): StateFlow<WeatherDomainModel> = localDataSource.weatherDataState

    override suspend fun refreshWeatherData(): Result<Unit> = networkDataSource
        .getWeatherData()
        .onSuccess {
            localDataSource.weatherDataState.update { _ ->
                it.toWeatherDomainModel()
            }
        }
        .map { }
}
