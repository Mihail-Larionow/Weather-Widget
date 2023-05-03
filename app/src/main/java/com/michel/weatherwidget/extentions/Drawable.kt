package com.michel.weatherwidget.extentions

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap


fun Drawable.toShapedBitmap(shape: Drawable, rect: Rect): Bitmap {
    val width = this.minimumWidth
    val height = this.minimumHeight

    val maskBm = shape.toBitmap(rect.width(), rect.height(), Bitmap.Config.ALPHA_8)
    val resultBm = maskBm.copy(Bitmap.Config.ARGB_8888, true)
    val srcBm = this.toBitmap(rect.width(), height * rect.width() / width, Bitmap.Config.ARGB_8888)
    val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    val resultCanvas = Canvas(resultBm)

    resultCanvas.drawBitmap(maskBm, rect, rect, null)
    resultCanvas.drawBitmap(srcBm, rect, rect, maskPaint)

    return resultBm
}