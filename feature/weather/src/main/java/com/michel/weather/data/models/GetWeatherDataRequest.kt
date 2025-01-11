package com.michel.weather.data.models

import com.michel.api.models.GetRequest

internal class GetWeatherDataRequest : GetRequest<GetWeatherDataResponse>(
    url = "https://api.open-meteo.com/v1/forecast",
    responseType = GetWeatherDataResponse::class.java,
    queryParams = queryParams,
)

private val queryParams = mapOf(
    "latitude" to 52.52.toString(),
    "longitude" to 13.41.toString(),
    "forecast_days" to 1.toString(),
    "hourly" to "temperature_2m,rain,snowfall",
)
