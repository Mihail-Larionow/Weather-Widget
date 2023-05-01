package com.michel.weatherwidget

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.michel.weatherwidget.extentions.pxToDp


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val weatherWidgetView = WeatherWidgetView(applicationContext)

        val background = findViewById<ConstraintLayout>(R.id.background)
        val weatherBackground = findViewById<ImageView>(R.id.weatherBackground)
        //background.setBackgroundColor(getColor(R.color.black))
        val weatherIcon = findViewById<ImageView>(R.id.weatherIcon)
        val temperatureText = findViewById<TextView>(R.id.temperatureText)
        weatherWidgetView.getWeather()
        temperatureText.text = "${weatherWidgetView.actualTemperature}\u2103"

        val displayMetrics = DisplayMetrics()

        // on below line we are getting metrics
        // for display using window manager.
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        // on below line we are getting height
        // and width using display metrics.
        val height = applicationContext.pxToDp(displayMetrics.heightPixels)

        val width = applicationContext.pxToDp(displayMetrics.widthPixels)

        println("${width}, ${height}")
        val resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(resultBitmap)
        weatherWidgetView.setSize(width, height)
        weatherWidgetView.drawBackGround(canvas = canvas)
        weatherBackground.setImageBitmap(resultBitmap)
    }

    private fun setBackground(){

    }



}