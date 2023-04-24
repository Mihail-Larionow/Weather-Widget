package com.michel.weatherwidget

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toDrawable
import com.michel.weatherwidget.ui.WeatherView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image = findViewById<ImageView>(R.id.imageView)
        val weatherWidgetView = WeatherWidgetView(applicationContext)
        weatherWidgetView.setSize(500, 500)
        image.setImageBitmap(weatherWidgetView.drawView(0f))
    }



}