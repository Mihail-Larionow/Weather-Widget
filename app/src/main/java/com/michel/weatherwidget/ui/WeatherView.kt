package com.michel.weatherwidget.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.michel.weatherwidget.R
import com.michel.weatherwidget.WeatherWidgetView
import com.michel.weatherwidget.extentions.dpToPx

class WeatherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttribute: Int = 0
): AppCompatImageView(context, attrs, defStyleAttribute){

    companion object{
        private const val DEFAULT_THEME = 0
        private const val DEFAULT_WIDTH = 40
        private const val DEFAULT_BORDER_WIDTH = 0
        private const val DEFAULT_CORNER_RADIUS = 20
    }

    private var weatherTheme = DEFAULT_THEME
    private var cornerRadius = 0f
    private var borderWidth = 0f
    private var backgroundImageSize = 0
    private var iconSize = 0
    private val weatherWidgetView = WeatherWidgetView(context)

    init{
        if(attrs != null){
            val ta = context.obtainStyledAttributes(attrs, R.styleable.WeatherView)
            weatherTheme = ta.getInteger(
                R.styleable.WeatherView_weather_theme,
                DEFAULT_THEME
            )

            borderWidth = ta.getDimension(
                R.styleable.WeatherView_corner_radius,
                context.dpToPx(DEFAULT_BORDER_WIDTH)
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
        weatherWidgetView.setTheme(weatherTheme)
        scaleType = ScaleType.CENTER_CROP
    }

    fun getTheme(): Int = weatherTheme
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val initWidth = resolveDefaultSize(widthMeasureSpec)
        val initHeight = resolveDefaultSize(heightMeasureSpec)
        setMeasuredDimension(initWidth, initHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        weatherWidgetView.setSize(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        weatherWidgetView.drawView(canvas, cornerRadius)
    }

    private fun resolveDefaultSize(spec: Int): Int = when(MeasureSpec.getMode(spec)){
        MeasureSpec.UNSPECIFIED -> context.dpToPx(DEFAULT_WIDTH).toInt()
        MeasureSpec.AT_MOST -> MeasureSpec.getSize(spec)
        MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
        else -> MeasureSpec.getSize(spec)
    }

}