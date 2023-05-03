package com.michel.weatherwidget

import com.michel.weatherwidget.retrofit.WeatherAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Weather (){

    private var cityName = "Moscow"
    private val key = BuildConfig.WEATHER_API_KEY

    var weather = "Clear"
    var temperature = 25
    var perceivedTemperature = 25

    private var weatherAPI: WeatherAPI


    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        weatherAPI = retrofit.create(WeatherAPI::class.java)
    }

    fun getWeather(){
        runBlocking {
            val response = weatherAPI.getProduct("weather?q=$cityName&appid=$key")
            weather = response.weather[0]["main"].toString()
            temperature = (response.main["temp"]?.minus(273.15))!!.toInt()
            perceivedTemperature = (response.main["feels_like"]?.minus(273.15))!!.toInt()
        }
    }

    fun setCity(cityName: String){
        this.cityName = cityName
    }


}