package com.michel.weatherwidget.extentions

import android.content.Context


fun Context.dpToPx(dp: Int): Float = dp.toFloat() * this.resources.displayMetrics.density