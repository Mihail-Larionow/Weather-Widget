package com.michel.mvi.extensions

import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowCompat

fun Window.setEdgeToEdge() {
    WindowCompat.setDecorFitsSystemWindows(this, false)
    setDimAmount(0f)
    setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT,
    )
}
