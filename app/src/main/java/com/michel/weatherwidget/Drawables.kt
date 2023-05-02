package com.michel.weatherwidget

import android.graphics.Color

data class Drawables (

    var WEATHER: Map<String, Int> = mapOf(
        "Clear" to R.drawable.sun,
        "Clouds" to R.drawable.clouds
    ),

    val BACKGROUND: Map<String, IntArray> = mapOf(
        "Clear" to intArrayOf(Color.parseColor("#00BFFF"), Color.parseColor("#87CEEB")),
        "Clouds" to intArrayOf(Color.parseColor("#DCDCDC"), Color.parseColor("#9ACEEB")),
        "Rain" to intArrayOf(Color.parseColor("#DCDCDC"), Color.parseColor("#9ACEEB")),
        "Snow" to intArrayOf(Color.parseColor("#DCDCDC"), Color.parseColor("#9ACEEB")),
    ),

    val THEME: Array<Int?> = arrayOf(null, R.drawable.yuppie, R.drawable.polina)
)