package com.michel.weatherwidget

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.michel.weatherwidget.adapters.RecyclerAdapter

class MainActivity : AppCompatActivity() {

    private val drawables = Drawables()
    private val cityNames = arrayListOf("Moscow", "Saint-Petersbourg", "Kostroma")

    private lateinit var cityName: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var background: ConstraintLayout
    private lateinit var recycler: RecyclerView

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
        recycler = findViewById(R.id.cities)

        recycler.adapter = RecyclerAdapter(cityNames)
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