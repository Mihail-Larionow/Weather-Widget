package com.michel.weatherwidget.retrofit

data class WeatherData(
    val coord: Map<String, Double>,
    val weather: List<Map<String, Any>>,
    val base: String,
    val main: Map<String, Double>,
    val visibility: Int,
    val wind: Map<String, Double>,
    val clouds: Map<String, Double>,
    val dt: Int,
    val sys: Map<String, Any>,
    val timezone:Int,
    val id: Int,
    val name: String,
    val cod: Int
)