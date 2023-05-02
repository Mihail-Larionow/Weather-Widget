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
    private lateinit var weatherWidgetView: WeatherWidgetView
    private val drawables = Drawables()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setBackground(weatherWidgetView.weather)
        setIcon(weatherWidgetView.weather)
    }

    private fun init(){
        weatherWidgetView = WeatherWidgetView(applicationContext)
        background = findViewById(R.id.background)
        weatherIcon = findViewById(R.id.weatherIcon)

        val temperatureText = findViewById<TextView>(R.id.temperatureText)
        weatherWidgetView.getWeather()
        temperatureText.text = "${weatherWidgetView.temperature}\u2103"

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