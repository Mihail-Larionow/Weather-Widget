package com.michel.mvi.compose

import android.content.Context
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import com.michel.mvi.extensions.configureWindowToEdgeToEdge

internal class EdgeToEdgeDialog(context: Context, themeResId: Int) : AppCompatDialog(context, themeResId) {

    init {
        // We hide the title bar for any style configuration. Otherwise, there will be a gap
        // above the bottom sheet when it is expanded.
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        configureWindowToEdgeToEdge()
    }
}
