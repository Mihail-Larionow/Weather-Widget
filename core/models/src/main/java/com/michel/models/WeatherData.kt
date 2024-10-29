package com.michel.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    @SerialName("latitude") val latitude: Double? = null,
    @SerialName("longitude") val longitude: Double? = null,
    @SerialName("generationtime_ms") val generationtimeMs: Double? = null,
    @SerialName("utc_offset_seconds") val utcOffsetSeconds: Int? = null,
    @SerialName("timezone") val timezone: String? = null,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String? = null,
    @SerialName("elevation") val elevation: Double? = null,
    @SerialName("hourly_units") val hourlyUnits: HourlyUnits? = HourlyUnits(),
    @SerialName("hourly") val hourly: Hourly? = Hourly()
)

@Serializable
data class Hourly(
    @SerialName("time") val time: List<String> = emptyList(),
    @SerialName("temperature_2m") val temperature2m: List<Double> = emptyList(),
    @SerialName("rain") val rain: List<Double> = emptyList(),
    @SerialName("snowfall") val snowfall: List<Double> = emptyList()
)

@Serializable
data class HourlyUnits(
    @SerialName("time") val time: String? = null,
    @SerialName("temperature_2m") val temperature2m: String? = null,
    @SerialName("rain") val rain: String? = null,
    @SerialName("snowfall") val snowfall: String? = null
)