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

class WeatherWidgetView (private val context: Context){

    var cityName = "Moscow"
    val key = BuildConfig.WEATHER_API_KEY
    val url = "https://api.openweathermap.org/data/2.5/weather?q=$cityName&appid=$key"

    private var weatherRectOffSet = 0f
    private var themeRectOffSet = 0f
    private var actualTemperatureX = 20f
    private var actualTemperatureY = 20f
    var actualWeather = "Clear"
    var actualTemperature = 25
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
    private var weatherTheme = 0

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
            right = (height * 0.8).toInt()
            bottom = (height * 0.8).toInt()
        }

        with(weatherIconRect){
            left = 0
            top = 0
            right = height / 2
            bottom = height / 2
        }

        prepareShaders(actualWeather)
        prepareText()
        resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    }

    fun drawView(canvas: Canvas? = null, cornerRadius: Float = 0f, borderWidth: Float = 0f): Bitmap{
        if(canvas == null) {
            return draw(Canvas(resultBitmap), cornerRadius, borderWidth)
        }
        return draw(canvas, cornerRadius, borderWidth)
    }

    private fun draw(canvas: Canvas, cornerRadius: Float, borderWidth: Float): Bitmap{
        drawBackGround(canvas, cornerRadius, borderWidth)
        drawImage(canvas, themeImageRect.toRectF(), themeImagePaint, themeRectOffSet, (viewRect.height() * 0.1).toFloat())
        drawImage(canvas, weatherIconRect.toRectF(), weatherIconPaint, weatherRectOffSet, (viewRect.height() * 3 / 16).toFloat())
        drawActualTemp(canvas)
        return resultBitmap
    }

    fun drawBackGround(canvas: Canvas, cornerRadius: Float = 0f, borderWidth: Float = 0f){
        canvas.drawRoundRect(viewRect.toRectF(), cornerRadius, cornerRadius, backgroundPaint)
    }

    //Draw image on canvas
    private fun drawImage(canvas: Canvas, rect: RectF, paint: Paint, offSetX: Float, offSetY: Float){
        canvas.translate(offSetX, offSetY)
        canvas.drawRect(rect, paint)
        canvas.translate(-offSetX, -offSetY)
    }
    //Draw actual temperature on canvas
    private fun drawActualTemp(canvas: Canvas) = canvas.drawText(
        "$actualTemperature\u2103", actualTemperatureX, actualTemperatureY, textPaint
    )

    private fun prepareShaders(currentWeather: String){
        val width = viewRect.width()
        val height = viewRect.height()

        if(width == 0 || height == 0) return

        val weatherIcon = ResourcesCompat.getDrawable(context.resources, DRAWABLES.WEATHER[currentWeather]!!, null)!!
        val themeImage = ResourcesCompat.getDrawable(context.resources, DRAWABLES.THEME[weatherTheme], null)!!

        weatherRectOffSet = (width - (height / 8) - weatherIconRect.width()).toFloat()
        themeRectOffSet = (height / 8).toFloat()

        prepareShader(weatherIconPaint, weatherIcon, weatherIconRect.width(), weatherIconRect.height())
        prepareShader(themeImagePaint, themeImage, themeImageRect.width(), themeImageRect.height())

        backgroundPaint.shader = LinearGradient(0f, 0f,
            width.toFloat(), height.toFloat(), DRAWABLES.BACKGROUND[actualWeather]!!.first,
            DRAWABLES.BACKGROUND[actualWeather]!!.second, Shader.TileMode.MIRROR
        )
    }

    private fun prepareShader(paint: Paint, drawable: Drawable, width: Int, height: Int){
        val srcBitmap = drawable.toBitmap(width, height, Bitmap.Config.ARGB_8888)
        paint.shader = BitmapShader(srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
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