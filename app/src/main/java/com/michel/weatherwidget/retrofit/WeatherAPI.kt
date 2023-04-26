package com.michel.weatherwidget.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherAPI {

    @GET
    suspend fun getProduct(@Url url: String): WeatherData
}