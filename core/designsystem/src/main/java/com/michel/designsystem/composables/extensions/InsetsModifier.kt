package com.michel.designsystem.composables.extensions

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import com.michel.designsystem.theme.WeatherTheme

fun Modifier.bottomInsetsPadding(): Modifier = this
    .composed {
        val density = LocalDensity.current

        val imeBottom = WindowInsets.ime.getBottom(density)
        val navigationBarBottom = WindowInsets.navigationBars.getBottom(density)

        val paddingPx = (imeBottom - navigationBarBottom).coerceAtLeast(0)
        val paddingDp by animateDpAsState(
            label = "Bottom sheet padding",
            targetValue = density.run { paddingPx.toDp() },
            animationSpec = tween(
                durationMillis = WeatherTheme.integers.durationS,
                easing = LinearEasing,
            )
        )

        padding(bottom = paddingDp)
    }
