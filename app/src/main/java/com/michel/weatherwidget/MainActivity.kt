package com.michel.weatherwidget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val weatherWidgetView = WeatherWidgetView(applicationContext)

        val background = findViewById<ConstraintLayout>(R.id.background)
        //background.setBackgroundColor(getColor(R.color.black))
        val weatherIcon = findViewById<ImageView>(R.id.weatherIcon)
        val temperatureText = findViewById<TextView>(R.id.temperatureText)
        weatherWidgetView.getWeather()
        temperatureText.text = "${weatherWidgetView.actualTemperature}\u2103"

    }



}