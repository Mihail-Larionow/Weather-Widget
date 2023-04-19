package com.michel.weatherwidget

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Weather {

    val cityName = "Moscow"
    val key = BuildConfig.WEATHER_API_KEY
    val url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + key

    companion object{
        const val MICHEL_THEME = 0
        const val YUPPIE_THEME = 1
    }

    var weatherTheme = 0

    fun setCity(city: String){

    }

    fun setTheme(themeId: Int){
        weatherTheme = themeId
    }

    private fun requestWeather(context: Context){
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                println(it)
            },
            {

            }
        )
        queue.add(request)
    }

    private fun parseResponse(response: String): String{
        val jsonObject = JSONObject(response)
        return jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")
    }

}