package com.michel.designsystem.composables.blur

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeDefaults.tint
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint

object BlurDefaults {

    fun blurStyle(
        backgroundColor: Color,
        blurRadius: Dp = 40.dp,
        tint: HazeTint = tint(backgroundColor),
        noiseFactor: Float = HazeDefaults.noiseFactor,
    ): HazeStyle = HazeDefaults.style(
        backgroundColor = backgroundColor,
        blurRadius = blurRadius,
        tint = tint,
        noiseFactor = noiseFactor,
    )
}
