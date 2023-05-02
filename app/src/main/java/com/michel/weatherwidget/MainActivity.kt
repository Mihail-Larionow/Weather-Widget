package com.michel.weatherwidget

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    private lateinit var background: ConstraintLayout
    private val drawables = Drawables()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setBackground()
    }

    private fun init(){
        val weatherWidgetView = WeatherWidgetView(applicationContext)
        background = findViewById(R.id.background)
        val weatherBackground = findViewById<ImageView>(R.id.weatherBackground)
        val weatherIcon = findViewById<ImageView>(R.id.weatherIcon)
        val temperatureText = findViewById<TextView>(R.id.temperatureText)
        weatherWidgetView.getWeather()
        weatherWidgetView.setSize(500, 500)
        temperatureText.text = "${weatherWidgetView.actualTemperature}\u2103"

    }

    private fun setBackground(){
        background.background = GradientDrawable(
            GradientDrawable.Orientation.BL_TR,
            drawables.BACKGROUND["Clear"]
        )
    }


}