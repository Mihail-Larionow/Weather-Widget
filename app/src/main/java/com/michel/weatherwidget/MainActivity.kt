package com.michel.weatherwidget

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.michel.weatherwidget.ui.WeatherView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image = findViewById<ImageView>(R.id.imageView)
        val weatherWidgetView = WeatherWidgetView()

        image.setImageBitmap(weatherWidgetView.drawView(500,500))
    }



}