package com.michel.weatherwidget

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    private val drawables = Drawables()

    private lateinit var cityName: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var background: ConstraintLayout

    companion object{
        const val GALLERY_REQ_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init(){
        val weather = Weather()
        background = findViewById(R.id.background)
        weatherIcon = findViewById(R.id.weatherIcon)
        cityName = findViewById(R.id.cityName)

        cityName.text = "Moscow"
        weather.setCity(cityName.text.toString())

        val temperatureText = findViewById<TextView>(R.id.temperatureText)
        weather.getWeather()
        temperatureText.text = "${weather.temperature}\u2103"

        setBackground(weather.weather)
        setIcon(weather.weather)
    }

    private fun setBackground(currentWeather: String){
        background.background = GradientDrawable(
            GradientDrawable.Orientation.BL_TR,
            drawables.BACKGROUND[currentWeather]
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setIcon(currentWeather: String){
        weatherIcon.setImageDrawable(resources.getDrawable(drawables.WEATHER[currentWeather]!!, null))
    }

}