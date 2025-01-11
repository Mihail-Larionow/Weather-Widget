package com.michel.weather.data.datasources

import com.michel.api.data.NetworkDataSource
import com.michel.weather.data.models.GetWeatherDataRequest
import com.michel.weather.data.models.GetWeatherDataResponse
import javax.inject.Inject

class WeatherNetworkDataSource @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) {

    suspend fun getWeatherData(): Result<GetWeatherDataResponse> =
        networkDataSource.networkRequest(
            request = GetWeatherDataRequest(),
        )
}
