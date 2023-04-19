package com.michel.weatherwidget.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.michel.weatherwidget.R
import com.michel.weatherwidget.extentions.dpToPx

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


    init{
        if(attrs != null){
            val ta = context.obtainStyledAttributes(attrs, R.styleable.WeatherWidgetView)
            borderWidth = ta.getDimension(
                R.styleable.WeatherWidgetView_borderWidth,
                context.dpToPx(DEFAULT_BORDER_WIDTH)
            )

            text = ta.getString(R.styleable.WeatherWidgetView_text) ?: "No text"
            ta.recycle()
        }

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

}