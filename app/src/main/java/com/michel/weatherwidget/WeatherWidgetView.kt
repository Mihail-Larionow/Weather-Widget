package com.michel.weatherwidget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class WeatherWidgetView (context: Context){

    val cityName = "Moscow"
    val key = BuildConfig.WEATHER_API_KEY
    val url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + key
    private val themeArray = arrayListOf<Map<String, Drawable?>>()
    private val weatherMap= mutableMapOf<String, Drawable?>()

    private var textOffSetX: Float = 20f
    private var textOffSetY: Float = 20f
    private var weather = "sunny"
    private var temperature = 25
    private val viewRect = Rect()
    private val weatherRect = Rect()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val viewPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val weatherPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var resultBitmap: Bitmap

    var weatherTheme = 0

    init{
        weatherMap["sunny"] = ResourcesCompat.getDrawable(context.resources, R.drawable.sunny, null)
        themeArray.add(mapOf("sunny" to ResourcesCompat.getDrawable(context.resources, R.drawable.sunny, null)))
        requestWeather(context)
    }

    private fun requestWeather(context: Context){
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                println(it)
                weather = getWeather(it)
                temperature = (getTemp(it) - 273.15).toInt()
                //prepareWeatherShader(viewRect.width(), viewRect.height())
            },
            {

            }
        )
        queue.add(request)
    }

    fun setCity(city: String){

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

        with(weatherRect){
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
        canvas.drawText(temperature.toString() + "\u2103", viewRect.width().toFloat() - textOffSetX, viewRect.height().toFloat() - textOffSetY, textPaint)
        return resultBitmap
    }

    private fun drawWeatherIcon(canvas: Canvas, size: Int){
        val weatherRectOffSet = (viewRect.width() - (size / 8) - weatherRect.width()).toFloat()
        canvas.translate(weatherRectOffSet, (size/4).toFloat())
        canvas.drawRect(weatherRect.toRectF(), weatherPaint)
        canvas.translate(-weatherRectOffSet, -(size/4).toFloat())
    }

    private fun prepareWeatherShader(currentWeather: String){
        val width = weatherRect.width()
        val height = weatherRect.height()

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

    private fun getWeather(response: String): String{
        val jsonObject = JSONObject(response)
        return jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")
    }

    private fun getTemp(response: String): Double{
        val jsonObject = JSONObject(response)
        return jsonObject.getJSONObject("main").getDouble("temp")
    }

}