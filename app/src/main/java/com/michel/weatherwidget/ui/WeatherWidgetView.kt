package com.michel.weatherwidget.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.michel.weatherwidget.R
import com.michel.weatherwidget.extentions.dpToPx
import org.json.JSONObject

class WeatherWidgetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttribute: Int = 0
): AppCompatImageView(context, attrs, defStyleAttribute){

    companion object{
        private const val DEFAULT_WIDTH = 40
        private const val DEFAULT_BORDER_WIDTH = 10
        private const val DEFAULT_CORNER_RADIUS = 20
    }

    private var borderWidth: Float = context.dpToPx(DEFAULT_BORDER_WIDTH)
    private var cornerRadius: Float = context.dpToPx(DEFAULT_CORNER_RADIUS)
    private var textOffSetX: Float = 20f
    private var textOffSetY: Float = 20f
    private var text: String = "No text"
    private val viewRect = Rect()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val weatherPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    val cityName = "Moscow"
    val key = "df5a1aced25d59f557c1352dee1f6c2f"
    val url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + key

    lateinit var canvasBitmap: Canvas

    init{
        if(attrs != null){
            val ta = context.obtainStyledAttributes(attrs, R.styleable.WeatherWidgetView)
            borderWidth = ta.getDimension(
                R.styleable.WeatherWidgetView_borderWidth,
                context.dpToPx(DEFAULT_BORDER_WIDTH)
            )

            ta.recycle()
        }

        requestWeather(this.context)

        scaleType = ScaleType.CENTER_CROP
        setup()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val initWidth = resolveDefaultSize(widthMeasureSpec)
        setMeasuredDimension(initWidth, (initWidth * 0.4f).toInt())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if(w==0) return
        with(viewRect){
            left = 0
            top = 0
            right = w
            bottom = h
        }

        prepareShader(w, h)
        prepareText(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        val half = (borderWidth / 2).toInt()
        viewRect.inset(half, half)
        canvas.drawRoundRect(viewRect.toRectF(), cornerRadius, cornerRadius, weatherPaint)
        canvas.drawRoundRect(viewRect.toRectF(), cornerRadius, cornerRadius, borderPaint)
        canvas.drawText(text, viewRect.width().toFloat() - textOffSetX, viewRect.height().toFloat() - textOffSetY, textPaint)
        canvasBitmap = canvas
    }

    private fun setup() {
        with(borderPaint){
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeWidth = borderWidth
        }
    }

    private fun prepareShader(w: Int, h: Int){
        if(w == 0 || h == 0 || drawable == null) return
        val srcBitmap = drawable.toBitmap(w, h, Bitmap.Config.ARGB_8888)
        weatherPaint.shader = BitmapShader(srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }

    private fun prepareText(w: Int, h: Int){
        with(textPaint){
            color = Color.WHITE
            textAlign = Paint.Align.RIGHT
            textSize = w * 0.05f
        }
        textOffSetX = w * 0.05f
        textOffSetY = h * 0.05f
    }


    private fun resolveDefaultSize(spec: Int): Int = when(MeasureSpec.getMode(spec)){
        MeasureSpec.UNSPECIFIED -> context.dpToPx(DEFAULT_WIDTH).toInt()
        MeasureSpec.AT_MOST -> MeasureSpec.getSize(spec)
        MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
        else -> MeasureSpec.getSize(spec)
    }

    private fun requestWeather(context: Context){
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                println(it)
                text = parseResponse(it)
            },
            {
                text = "Отсутствует соединение"
            }
        )
        queue.add(request)
    }

    private fun parseResponse(response: String): String{
        val jsonObject = JSONObject(response)
        return jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")
    }

}