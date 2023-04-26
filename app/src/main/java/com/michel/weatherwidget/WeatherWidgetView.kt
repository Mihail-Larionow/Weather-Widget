package com.michel.weatherwidget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.michel.weatherwidget.retrofit.WeatherAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherWidgetView (context: Context){

    var cityName = "Moscow"
    val key = BuildConfig.WEATHER_API_KEY
    val url = "https://api.openweathermap.org/data/2.5/weather?q=$cityName&appid=$key"
    private val themeArray = arrayListOf<Map<String, Drawable?>>()
    private val weatherMap= mutableMapOf<String, Drawable?>()

    private var textOffSetX: Float = 20f
    private var textOffSetY: Float = 20f
    private var weather = "sunny"
    private var temperature = 25
    private val viewRect = Rect()
    private val weatherIconRect = Rect()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val viewPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val weatherPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var resultBitmap: Bitmap
    private var weatherAPI: WeatherAPI


    var weatherTheme = 0

    init{
        weatherMap["sunny"] = ResourcesCompat.getDrawable(context.resources, R.drawable.sunny, null)
        weatherMap["Clouds"] = ResourcesCompat.getDrawable(context.resources, R.drawable.clouds, null)
        themeArray.add(mapOf("sunny" to ResourcesCompat.getDrawable(context.resources, R.drawable.sunny, null)))
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        weatherAPI = retrofit.create(WeatherAPI::class.java)
    }

    fun getWeather(){
        runBlocking {
            val product = weatherAPI.getProduct("weather?q=$cityName&appid=$key")
            weather = product.weather[0]["main"].toString()
            temperature = (product.main["temp"]?.minus(273.15))!!.toInt()
            println(weather)
        }
    }

    fun setCity(cityName: String){
        this.cityName = cityName
    }

    fun setTheme(themeId: Int){
        weatherTheme = themeId
    }

    fun setSize(width: Int, height: Int){
        with(viewRect){
            left = 0
            top = 0
            right = width
            bottom = height
        }

        with(weatherIconRect){
            left = 0
            top = 0
            right = height / 2
            bottom = height / 2
        }

        prepareWeatherShader(weather)
        prepareThemeShader(weatherTheme)
        prepareText(width, height)
        resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    }

    fun draw(canvas: Canvas? = null, cornerRadius: Float): Bitmap{
        if(canvas == null) {
            return drawWidget(Canvas(resultBitmap), cornerRadius)
        }
        return drawWidget(canvas, cornerRadius)
    }

    private fun drawWidget(canvas: Canvas, cornerRadius: Float): Bitmap{
        canvas.drawRoundRect(viewRect.toRectF(), cornerRadius, cornerRadius, viewPaint)
        drawWeatherIcon(canvas, viewRect.height())
        canvas.drawText("$temperature\u2103", viewRect.width().toFloat() - textOffSetX, viewRect.height().toFloat() - textOffSetY, textPaint)
        return resultBitmap
    }

    private fun drawWeatherIcon(canvas: Canvas, size: Int){
        val weatherRectOffSet = (viewRect.width() - (size / 8) - weatherIconRect.width()).toFloat()
        canvas.translate(weatherRectOffSet, (size/4).toFloat())
        canvas.drawRect(weatherIconRect.toRectF(), weatherPaint)
        canvas.translate(-weatherRectOffSet, -(size/4).toFloat())
    }

    private fun prepareWeatherShader(currentWeather: String){
        val width = weatherIconRect.width()
        val height = weatherIconRect.height()

        if(width == 0 || height == 0 || weatherMap[currentWeather] == null) return
        val srcBitmap = weatherMap[currentWeather]!!.toBitmap(width, height, Bitmap.Config.ARGB_8888)
        weatherPaint.shader = BitmapShader(srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }

    private fun prepareThemeShader(currentTheme: Int){
        val width = viewRect.width().toFloat()
        val height = viewRect.height().toFloat()

        viewPaint.shader = LinearGradient(0f, 0f,
            width, height, Color.parseColor("#00BFFF"),
            Color.parseColor("#87CEEB"), Shader.TileMode.MIRROR
        )

    }

    private fun prepareText(w: Int, h: Int){
        with(textPaint){
            color = Color.WHITE
            textAlign = Paint.Align.RIGHT
            textSize = h * 0.2f
        }
        textOffSetX = h * 0.1f
        textOffSetY = h * 0.1f
    }


}