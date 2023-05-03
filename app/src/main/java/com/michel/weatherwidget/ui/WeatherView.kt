package com.michel.weatherwidget.ui

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.toRectF
import com.michel.weatherwidget.Drawables
import com.michel.weatherwidget.R
import com.michel.weatherwidget.Weather
import com.michel.weatherwidget.extentions.dpToPx
import com.michel.weatherwidget.extentions.toShapedBitmap

@Suppress("DEPRECATION")
class WeatherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttribute: Int = 0
): AppCompatImageView(context, attrs, defStyleAttribute){

    companion object{
        private const val DEFAULT_WIDTH = 40
        private const val DEFAULT_CORNER_RADIUS = 20
        private val DRAWABLES = Drawables()

    }

    private val viewRect = Rect()
    private val mainIconRect = Rect()
    private val weatherIconRect = Rect()

    private var theme = 0
    private var iconSize = 0
    private var cornerRadius = 0f
    private var backgroundImageSize = 0

    private val weather = Weather()

    private lateinit var iconBitmap: Bitmap
    private lateinit var resultBitmap: Bitmap
    private lateinit var weatherBitmap: Bitmap

    private var themeImage: Drawable? = null
    private var weatherIcon = ResourcesCompat.getDrawable(context.resources, DRAWABLES.WEATHER["Clear"]!!, null)!!

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init{
        if(attrs != null){
            val ta = context.obtainStyledAttributes(attrs, R.styleable.WeatherView)
            theme = ta.getResourceId(
                R.styleable.WeatherView_weather_theme,
                0
            )

            cornerRadius = ta.getDimension(
                R.styleable.WeatherView_corner_radius,
                context.dpToPx(DEFAULT_CORNER_RADIUS)
            )

            iconSize = ta.getInteger(
                R.styleable.WeatherView_icon_size,
                0
            )

            backgroundImageSize = ta.getInteger(
                R.styleable.WeatherView_background_image_size,
                0
            )

            ta.recycle()
        }
        scaleType = ScaleType.CENTER_CROP

        weather.getWeather()
        weatherIcon = ResourcesCompat.getDrawable(context.resources, DRAWABLES.WEATHER[weather.weather]!!, null)!!
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val initWidth = resolveDefaultSize(widthMeasureSpec)
        val initHeight = resolveDefaultSize(heightMeasureSpec)
        setMeasuredDimension(initWidth, initHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        setSize(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        drawView(canvas, cornerRadius)
    }

    fun drawView(canvas: Canvas, cornerRadius: Float){
        drawBackGround(canvas, cornerRadius)

        if(weather.weather != "Clear" && weather.weather != "None") {
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
        }

        drawImage(
            canvas, mainIconRect, iconBitmap,
            viewRect.height() * 0.05f,
            viewRect.height() * 0.05f
        )

        val text: String = if(weather.weather == "None") "wait for connection"
        else "${weather.temperature}\u2103"

        drawText(
            canvas, text,
            viewRect.width() - viewRect.height() * 0.1f,
            viewRect.height() * 0.5f
        )
    }

    fun setTheme(path: Uri? = null){
        themeImage = if(path == null) null
        else BitmapDrawable(MediaStore.Images.Media.getBitmap(context.contentResolver, path))
    }

    fun getTheme(): Int = theme

    fun setSize(w: Int, h: Int){
        with(viewRect){
            left = 0
            top = 0
            right = w
            bottom = h
        }

        with(weatherIconRect){
            left = 0
            top = 0
            right = (h * 1.6).toInt()
            bottom = (h * 1.6).toInt()
        }

        with(mainIconRect){
            left = 0
            top = 0
            right = (h * 0.9).toInt()
            bottom = (h * 0.9).toInt()
        }

        resultBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        if(themeImage != null) {
            weatherBitmap = themeImage!!.toShapedBitmap(weatherIcon, weatherIconRect)
            iconBitmap = themeImage!!.toShapedBitmap(weatherIcon, mainIconRect)
        }
        else {
            weatherBitmap = weatherIcon.toShapedBitmap(weatherIcon, weatherIconRect)
            iconBitmap = weatherIcon.toShapedBitmap(weatherIcon, mainIconRect)
        }

        prepareShaders()
        prepareText()
    }

    private fun resolveDefaultSize(spec: Int): Int = when(MeasureSpec.getMode(spec)){
        MeasureSpec.UNSPECIFIED -> context.dpToPx(DEFAULT_WIDTH).toInt()
        MeasureSpec.AT_MOST -> MeasureSpec.getSize(spec)
        MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
        else -> MeasureSpec.getSize(spec)
    }


    //Draw background on canvas
    private fun drawBackGround(canvas: Canvas, cornerRadius: Float = 0f){
        canvas.drawRoundRect(viewRect.toRectF(), cornerRadius, cornerRadius, backgroundPaint)
    }

    //Draw image on canvas
    private fun drawImage(canvas: Canvas, rect: Rect, image: Bitmap, offSetX: Float, offSetY: Float){
        canvas.translate(offSetX, offSetY)
        canvas.drawBitmap(image, rect, rect, null)
        canvas.translate(-offSetX, -offSetY)
    }

    private fun drawText(canvas: Canvas, text: String, offSetX: Float, offSetY: Float) = canvas.drawText(
        text, offSetX, offSetY, textPaint
    )

    private fun prepareShaders(){
        val width = viewRect.width().toFloat()
        val height = viewRect.height().toFloat()

        backgroundPaint.shader = LinearGradient(0f, 0f,
            width, height, DRAWABLES.BACKGROUND[weather.weather]!![0],
            DRAWABLES.BACKGROUND[weather.weather]!![1], Shader.TileMode.MIRROR
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