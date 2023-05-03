package com.michel.weatherwidget.extentions

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap

fun Context.dpToPx(dp: Int): Float = dp.toFloat() * this.resources.displayMetrics.density

fun Context.pxToDp(px: Int): Int = (px / this.resources.displayMetrics.density).toInt()

fun Drawable.toShapedBitmap(shape: Drawable, rect: Rect): Bitmap {
    val maskBm = shape.toBitmap(rect.width(), rect.height(), Bitmap.Config.ALPHA_8)
    val resultBm = maskBm.copy(Bitmap.Config.ARGB_8888, true)
    var srcBm = shape.toBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888)
    if(this != null) srcBm = this.toBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888)
    val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    val resultCanvas = Canvas(resultBm)

    resultCanvas.drawBitmap(maskBm, rect, rect, null)
    resultCanvas.drawBitmap(srcBm, rect, rect, maskPaint)

    return resultBm
}