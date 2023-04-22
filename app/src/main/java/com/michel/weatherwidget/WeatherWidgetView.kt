package com.michel.weatherwidget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toRectF
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class WeatherWidgetView {

    val cityName = "Moscow"
    val key = BuildConfig.WEATHER_API_KEY
    val url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + key
    val themeArray = arrayListOf<Map<String, Int>>()

    companion object{
        const val MICHEL_THEME = 0
        const val YUPPIE_THEME = 1
    }

    var weatherTheme = 0

    fun setCity(city: String){

    }

    fun setTheme(themeId: Int){
        weatherTheme = themeId
    }

    fun addTheme(theme: Map<String, Int>){
        themeArray.add(theme)
    }

    fun drawView(w: Int, h: Int): Bitmap{
        val drawable = R.drawable.ic_launcher_foreground
        val srcBitmap = drawable.toDrawable().toBitmap(w, h, Bitmap.Config.ARGB_8888)
        val viewRect = Rect()
        with(viewRect){
            left = 0
            top = 0
            right = w
            bottom = h
        }
        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        val weatherPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        weatherPaint.shader = BitmapShader(srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        var resultBitmap: Bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(resultBitmap)
        canvas.drawRoundRect(viewRect.toRectF(), 40f, 40f, weatherPaint)
        return resultBitmap
    }

    private fun requestWeather(context: Context){
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                println(it)

            },
            {

            }
        )
        queue.add(request)
    }

    private fun parseResponse(response: String): String{
        val jsonObject = JSONObject(response)
        return jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")
    }



}