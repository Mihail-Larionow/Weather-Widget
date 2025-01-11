package com.michel.weather.data.models

import com.google.gson.annotations.SerializedName

data class GetWeatherDataResponse(

    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("generationtime_ms") var generationtimeMs: Double? = null,
    @SerializedName("utc_offset_seconds") var utcOffsetSeconds: Int? = null,
    @SerializedName("timezone") var timezone: String? = null,
    @SerializedName("timezone_abbreviation") var timezoneAbbreviation: String? = null,
    @SerializedName("elevation") var elevation: Int? = null,
    @SerializedName("hourly_units") var hourlyUnits: HourlyUnits? = HourlyUnits(),
    @SerializedName("hourly") var hourly: Hourly? = Hourly()

)

data class HourlyUnits(

    @SerializedName("time") var time: String? = null,
    @SerializedName("temperature_2m") var temperature2m: String? = null,
    @SerializedName("rain") var rain: String? = null,
    @SerializedName("snowfall") var snowfall: String? = null

)

data class Hourly(

    @SerializedName("time") var time: ArrayList<String> = arrayListOf(),
    @SerializedName("temperature_2m") var temperature2m: ArrayList<Double> = arrayListOf(),
    @SerializedName("rain") var rain: ArrayList<Double> = arrayListOf(),
    @SerializedName("snowfall") var snowfall: ArrayList<Double> = arrayListOf()

)
