package com.michel.mvi.extensions

import androidx.activity.ComponentDialog

fun ComponentDialog.configureWindowToEdgeToEdge() {
    window?.setEdgeToEdge() ?: error("Cannot configure window")
}
