package com.michel.weatherwidget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image = findViewById<ImageView>(R.id.imageView)
        val weatherWidgetView = WeatherWidgetView(applicationContext)
        weatherWidgetView.getWeather()

        weatherWidgetView.setSize(500, 500)
        image.setImageBitmap(weatherWidgetView.draw(cornerRadius = 0f))


    }



}