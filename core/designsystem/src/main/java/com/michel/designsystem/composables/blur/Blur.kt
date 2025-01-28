package com.michel.designsystem.composables.blur

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.chrisbanes.haze.HazeChildScope
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@Composable
fun Modifier.blurredBackground(
    hazeState: HazeState,
    blurBackgroundColor: Color,
    fallbackColor: Color,
    style: HazeStyle = BlurDefaults.blurStyle(blurBackgroundColor),
    block: (HazeChildScope.() -> Unit)? = null,
): Modifier = if (HazeDefaults.blurEnabled()) {
    this.hazeChild(
        state = hazeState,
        style = style,
        block = block,
    )
} else {
    this.background(color = fallbackColor)
}

fun Modifier.blurrableContent(state: HazeState): Modifier = this.haze(state = state)
