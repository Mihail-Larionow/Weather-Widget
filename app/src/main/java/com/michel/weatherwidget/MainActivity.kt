package com.michel.weatherwidget

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.michel.weatherwidget.ui.WeatherWidgetView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image = findViewById<ImageView>(R.id.imageView)
        var bitmap: Bitmap = Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val weatherWidgetView = findViewById<WeatherWidgetView>(R.id.widget)
        weatherWidgetView.drawView(canvas)
        image.setImageBitmap(bitmap)
    }



}