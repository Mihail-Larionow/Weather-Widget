package com.michel.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Immutable
data class WeatherTypography(
    val title1: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    val title2: TextStyle = TextStyle(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    val body1: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    val body2: TextStyle = TextStyle(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    val body3: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
)
