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

    var actualWeather = "Clear"
    var actualTemperature = 25
    var perceivedTemperature = 25
    private val viewRect = Rect()
    private val mainIconRect = Rect()
    private val weatherIconRect = Rect()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var resultBitmap: Bitmap
    private var weatherAPI: WeatherAPI
    private var weatherTheme = 0
    private lateinit var weatherBitmap: Bitmap
    private lateinit var iconBitmap: Bitmap

    private var weatherIcon = ResourcesCompat.getDrawable(context.resources, DRAWABLES.WEATHER[actualWeather]!!, null)!!
    private var themeImage = ResourcesCompat.getDrawable(context.resources, DRAWABLES.THEME[weatherTheme], null)!!

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
            weatherIcon = ResourcesCompat.getDrawable(context.resources, DRAWABLES.WEATHER[actualWeather]!!, null)!!
            themeImage = ResourcesCompat.getDrawable(context.resources, DRAWABLES.THEME[weatherTheme], null)!!
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
            right = (height * 1.6).toInt()
            bottom = (height * 1.6).toInt()
        }

        with(mainIconRect){
            left = 0
            top = 0
            right = (height * 0.9).toInt()
            bottom = (height * 0.9).toInt()
        }

        prepareShaders()
        prepareText()

        resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        weatherBitmap = drawShapeImage(weatherIcon, themeImage, weatherIconRect)
        iconBitmap = drawShapeImage(weatherIcon, themeImage, mainIconRect)
    }

    fun drawView(canvas: Canvas? = null, cornerRadius: Float = 0f, borderWidth: Float = 0f): Bitmap{
        if(canvas == null) {
            return draw(Canvas(resultBitmap), cornerRadius, borderWidth)
        }
        return draw(canvas, cornerRadius, borderWidth)
    }

    private fun draw(canvas: Canvas, cornerRadius: Float, borderWidth: Float): Bitmap{
        drawBackGround(canvas, cornerRadius, borderWidth)

        //if(actualWeather != "Clear") {

            drawImage(
                canvas, weatherIconRect, weatherBitmap,
                viewRect.width() * 0.5f - weatherIconRect.width() * 0.5f,
                -weatherIconRect.height() * 0.6f
            )

            drawImage(
                canvas, weatherIconRect, weatherBitmap,
                viewRect.width() - weatherIconRect.width() - viewRect.width() * 0.025f,
                viewRect.height() - weatherIconRect.height() * 0.4f
            )

        //}

        drawImage(
            canvas, mainIconRect, iconBitmap,
            viewRect.height() * 0.05f,
            viewRect.height() * 0.05f
        )

        drawActualTemp(canvas)
        return resultBitmap
    }

    fun drawBackGround(canvas: Canvas, cornerRadius: Float = 0f, borderWidth: Float = 0f){
        canvas.drawRoundRect(viewRect.toRectF(), cornerRadius, cornerRadius, backgroundPaint)
    }

    private fun drawShapeImage(shape: Drawable, image: Drawable, rect: Rect): Bitmap{
        val maskBm = shape.toBitmap(rect.width(), rect.height(), Bitmap.Config.ALPHA_8)
        val resultBm = maskBm.copy(Bitmap.Config.ARGB_8888, true)
        val srcBm = image.toBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888)
        val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        val maskCanvas = Canvas(maskBm)
        maskCanvas.drawBitmap(maskBm, rect, rect, maskPaint)
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        val resultCanvas = Canvas(resultBm)

        resultCanvas.drawBitmap(maskBm, rect, rect, null)
        resultCanvas.drawBitmap(srcBm, rect, rect, maskPaint)

        return resultBm
    }

    //Draw image on canvas
    private fun drawImage(canvas: Canvas, rect: Rect, image: Bitmap, offSetX: Float, offSetY: Float){
        canvas.translate(offSetX, offSetY)
        canvas.drawBitmap(image, rect, rect, null)
        canvas.translate(-offSetX, -offSetY)
    }
    //Draw actual temperature on canvas
    private fun drawActualTemp(canvas: Canvas) = canvas.drawText(
        "$actualTemperature\u2103", viewRect.width() - viewRect.height() * 0.1f, viewRect.height() * 0.5f, textPaint
    )

    private fun prepareShaders(){
        val width = viewRect.width().toFloat()
        val height = viewRect.height().toFloat()

        backgroundPaint.shader = LinearGradient(0f, 0f,
            width, height, DRAWABLES.BACKGROUND[actualWeather]!!.first,
            DRAWABLES.BACKGROUND[actualWeather]!!.second, Shader.TileMode.MIRROR
        )
    }


    private fun prepareText(){
        with(textPaint){
            color = Color.WHITE
            textAlign = Paint.Align.RIGHT
            textSize = viewRect.height() * 0.5f
        }
    }

}