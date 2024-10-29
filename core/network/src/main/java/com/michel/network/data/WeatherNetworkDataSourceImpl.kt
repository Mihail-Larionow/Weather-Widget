package com.michel.network.data

import com.michel.models.WeatherData
import com.michel.network.api.client
import com.michel.network.api.ktor.safeRequest
import io.ktor.util.StringValues
import javax.inject.Inject

class WeatherNetworkDataSourceImpl @Inject constructor() : WeatherNetworkDataSource {

    override suspend fun getWeather(): Result<WeatherData> = client.safeRequest(
        host = "api.open-meteo.com",
        path = "/v1/forecast",
        params = StringValues.build {
            append("latitude", 52.52.toString())
            append("longitude", 13.41.toString())
            append("forecast_days", 1.toString())
            append("hourly", "temperature_2m,rain,snowfall")
        }
    )
}