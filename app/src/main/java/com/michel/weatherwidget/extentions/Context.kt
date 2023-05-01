package com.michel.weatherwidget.extentions

import android.content.Context

fun Context.dpToPx(dp: Int): Float = dp.toFloat() * this.resources.displayMetrics.density

fun Context.pxToDp(px: Int): Int = (px / this.resources.displayMetrics.density).toInt()
