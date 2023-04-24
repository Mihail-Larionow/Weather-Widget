package com.michel.weatherwidget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toRectF
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.michel.weatherwidget.extentions.dpToPx
import com.michel.weatherwidget.ui.WeatherView
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
    private var temperature: Double = 25.0
    private val viewRect = Rect()
    private val weatherRect = Rect()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val viewPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val weatherPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    var weatherTheme = 0

    init{
        weatherMap.set("sunny", ResourcesCompat.getDrawable(context.resources, R.drawable.sunny, null))
        themeArray.add(mapOf("sunny" to ResourcesCompat.getDrawable(context.resources, R.drawable.sunny, null)))
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

        prepareWeatherShader(height/2, height/2)
        prepareThemeShader(width, height)
        prepareText(width, height)
    }

    fun drawView(cornerRadius: Float): Bitmap{
        val resultBitmap: Bitmap = Bitmap.createBitmap(viewRect.width(), viewRect.height(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(resultBitmap)

        canvas.drawRoundRect(viewRect.toRectF(), cornerRadius, cornerRadius, viewPaint)
        drawWeatherIcon(canvas, viewRect.height())
        canvas.drawText(temperature.toString(), viewRect.width().toFloat() - textOffSetX, viewRect.height().toFloat() - textOffSetY, textPaint)
        return resultBitmap
    }

    fun drawView(canvas: Canvas, cornerRadius: Float){
        canvas.drawRoundRect(viewRect.toRectF(), cornerRadius, cornerRadius, viewPaint)
        drawWeatherIcon(canvas, viewRect.height())
        canvas.drawText(temperature.toString(), viewRect.width().toFloat() - textOffSetX, viewRect.height().toFloat() - textOffSetY, textPaint)
    }

    fun getWidgetWidth(widgetId: Int, context: Context): Int {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        return if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            appWidgetManager.getAppWidgetOptions(widgetId)
                .getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 0)
        } else {
            appWidgetManager.getAppWidgetOptions(widgetId)
                .getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH, 0)
        }
    }

    fun getWidgetHeight(widgetId: Int, context: Context): Int {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        return if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            appWidgetManager.getAppWidgetOptions(widgetId)
                .getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT, 0)
        } else {
            appWidgetManager.getAppWidgetOptions(widgetId)
                .getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT, 0)
        }
    }

    private fun drawWeatherIcon(canvas: Canvas, size: Int){
        val weatherRectOffSet = (viewRect.width() - (size / 8) - weatherRect.width()).toFloat()
        canvas.translate(weatherRectOffSet, (size/4).toFloat())
        canvas.drawRect(weatherRect.toRectF(), weatherPaint)
        canvas.translate(-weatherRectOffSet, -(size/4).toFloat())
    }

    private fun prepareWeatherShader(w: Int, h: Int){
        with(viewPaint){
            color = Color.BLUE
            style = Paint.Style.FILL
        }

        if(w == 0 || h == 0 || weatherMap[weather] == null) return
        val srcBitmap = weatherMap[weather]!!.toBitmap(w, h, Bitmap.Config.ARGB_8888)
        weatherPaint.shader = BitmapShader(srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }

    private fun prepareThemeShader(w: Int, h: Int){
        viewPaint.shader = LinearGradient(0f, 0f,
            w.toFloat(), h.toFloat(), Color.parseColor("#00BFFF"),
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

    fun requestWeather(context: Context){
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                println(it)
                weather = getWeather(it)
                temperature = getTemp(it) - 273.15
                //prepareWeatherShader(viewRect.width(), viewRect.height())
            },
            {

            }
        )
        queue.add(request)
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