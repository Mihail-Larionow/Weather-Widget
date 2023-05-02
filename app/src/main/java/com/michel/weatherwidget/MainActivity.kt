package com.michel.weatherwidget

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    private lateinit var background: ConstraintLayout
    private lateinit var weatherIcon: ImageView
    private lateinit var weatherIt: WeatherIt
    private lateinit var cityName: TextView
    private val drawables = Drawables()

    companion object{
        const val GALLERY_REQ_CODE = 1000
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setBackground(weatherIt.weather)
        setIcon(weatherIt.weather)
    }

    private fun init(){
        weatherIt = WeatherIt(applicationContext)
        background = findViewById(R.id.background)
        weatherIcon = findViewById(R.id.weatherIcon)
        cityName = findViewById(R.id.cityName)

        cityName.text = "Moscow"
        weatherIt.setCity(cityName.text.toString())

        val temperatureText = findViewById<TextView>(R.id.temperatureText)
        weatherIt.getWeather()
        temperatureText.text = "${weatherIt.temperature}\u2103"

    }

    private fun setBackground(currentWeather: String){
        background.background = GradientDrawable(
            GradientDrawable.Orientation.BL_TR,
            drawables.BACKGROUND[currentWeather]
        )
    }

    private fun setIcon(currentWeather: String){
        weatherIcon.setImageDrawable(resources.getDrawable(drawables.WEATHER[currentWeather]!!, null))
    }


}