package com.michel.weatherwidget

import android.graphics.Color

data class Drawables (

    var WEATHER: Map<String, Int> = mapOf(
        "Clear" to R.drawable.sun,
        "Clouds" to R.drawable.clouds
    ),

    val BACKGROUND: Map<String, Pair<Int, Int>> = mapOf(
        "Clear" to Pair(Color.parseColor("#00BFFF"), Color.parseColor("#87CEEB")),
        "Clouds" to Pair(Color.parseColor("#DCDCDC"), Color.parseColor("#9ACEEB"))
    ),

    val THEME: Array<Int> = arrayOf(R.drawable.yuppie)
)