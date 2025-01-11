package com.michel.designsystem.composables.extensions

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.material.ripple
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Stable
fun travelRipple(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified,
): IndicationNodeFactory = ripple(
    bounded = bounded,
    radius = radius,
    color = color,
)
