package com.michel.weatherwidget

import android.content.Context
import android.graphics.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.michel.weatherwidget.retrofit.WeatherAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherWidgetView (private val context: Context){

    var cityName = "Moscow"
    val key = BuildConfig.WEATHER_API_KEY
    val url = "https://api.openweathermap.org/data/2.5/weather?q=$cityName&appid=$key"

    private var actualTemperatureX: Float = 20f
    private var actualTemperatureY: Float = 20f
    private var actualWeather = "Clear"
    private var actualTemperature = 25
    private var perceivedTemperature = 25
    private val viewRect = Rect()
    private val themeImageRect = Rect()
    private val weatherIconRect = Rect()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val themeImagePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val weatherIconPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var resultBitmap: Bitmap
    private var weatherAPI: WeatherAPI


    var weatherTheme = 0

    companion object{
        val DRAWABLES = Drawables()
    }

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        weatherAPI = retrofit.create(WeatherAPI::class.java)
    }

    fun getWeather(){
        runBlocking {
            val response = weatherAPI.getProduct("weather?q=$cityName&appid=$key")
            println(response.toString())
            actualWeather = response.weather[0]["main"].toString()
            actualTemperature = (response.main["temp"]?.minus(273.15))!!.toInt()
            perceivedTemperature = (response.main["feels_like"]?.minus(273.15))!!.toInt()
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

        with(themeImageRect){
            left = 0
            top = 0
            right = width * 3 / 4
            bottom = width * 3 / 4
        }

        with(weatherIconRect){
            left = 0
            top = 0
            right = height / 2
            bottom = height / 2
        }

        prepareWeatherShader(actualWeather)
        prepareText()
        resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    }

    fun draw(canvas: Canvas? = null, cornerRadius: Float): Bitmap{
        if(canvas == null) {
            return drawWidget(Canvas(resultBitmap), cornerRadius)
        }
        return drawWidget(canvas, cornerRadius)
    }

    private fun drawWidget(canvas: Canvas, cornerRadius: Float): Bitmap{
        canvas.drawRoundRect(viewRect.toRectF(), cornerRadius, cornerRadius, backgroundPaint)
        drawThemeImage(canvas)
        drawWeatherIcon(canvas)

        canvas.drawText("$actualTemperature\u2103", actualTemperatureX, actualTemperatureY, textPaint)
        return resultBitmap
    }

    private fun drawWeatherIcon(canvas: Canvas){
        val weatherRectOffSet = (viewRect.width() - (viewRect.height() / 8) - weatherIconRect.width()).toFloat()
        canvas.translate(weatherRectOffSet, (viewRect.height() * 3 / 16).toFloat())
        canvas.drawRect(weatherIconRect.toRectF(), weatherIconPaint)
        canvas.translate(-weatherRectOffSet, -(viewRect.height() * 3 / 16).toFloat())
    }

    private fun drawThemeImage(canvas: Canvas){
        val themeRectOffSet = (viewRect.width() / 2 - themeImageRect.width() / 2).toFloat()
        canvas.translate(themeRectOffSet, (viewRect.height()  / 4).toFloat())
        canvas.drawRect(themeImageRect.toRectF(), themeImagePaint)
        canvas.translate(-themeRectOffSet, -(viewRect.height() / 4).toFloat())
    }

    private fun prepareWeatherShader(currentWeather: String){
        val width = viewRect.width()
        val height = viewRect.height()

        val weatherIcon = ResourcesCompat.getDrawable(context.resources, DRAWABLES.WEATHER[currentWeather]!!, null)!!
        val themeImage = ResourcesCompat.getDrawable(context.resources, DRAWABLES.THEME[0], null)!!

        if(width == 0 || height == 0) return

        var srcBitmap = weatherIcon.toBitmap(height/2, height/2, Bitmap.Config.ARGB_8888)
        weatherIconPaint.shader = BitmapShader(srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        srcBitmap = themeImage.toBitmap(themeImageRect.width(), themeImageRect.height(), Bitmap.Config.ARGB_8888)
        themeImagePaint.shader = BitmapShader(srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        backgroundPaint.shader = LinearGradient(0f, 0f,
            width.toFloat(), height.toFloat(), DRAWABLES.BACKGROUND[actualWeather]!!.first,
            DRAWABLES.BACKGROUND[actualWeather]!!.second, Shader.TileMode.MIRROR
        )
    }


    private fun prepareText(){
        with(textPaint){
            color = Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = viewRect.height() * 0.15f
        }
        actualTemperatureX = (viewRect.width() - (viewRect.height() / 8) - weatherIconRect.width()/2).toFloat()
        actualTemperatureY = viewRect.height() * 0.85f
    }


}