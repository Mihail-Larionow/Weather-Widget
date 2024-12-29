package com.michel.ui.theme

import androidx.compose.ui.graphics.Color

fun lightColors(
    textPrimary: Color = Color(0xFF0D0D0F),
    textSecondary: Color = Color(0xFF9C9BA2),
    textDisabled: Color = Color(0xFFCDCCCF),
    backgroundLine: Color = Color(0xFFF5F5F5),
    backgroundPlaceholder: Color = Color(0xFFE6E5E7),
    backgroundSkeleton: Color = Color(0xFFF5F5F5),
    backgroundPrimary: Color = Color(0xFFF5F5F5),
    backgroundSecondary: Color = Color(0xFFFFFFFF),
    backgroundTertiary: Color = Color(0xFFF5F5F5),
    iconsPrimary: Color = Color(0xFF0D0D0F),
    iconsSecondary: Color = Color(0xFF9C9BA2),
) = WeatherColors(
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    textDisabled = textDisabled,
    backgroundLine = backgroundLine,
    backgroundPlaceholder = backgroundPlaceholder,
    backgroundSkeleton = backgroundSkeleton,
    backgroundPrimary = backgroundPrimary,
    backgroundSecondary = backgroundSecondary,
    backgroundTertiary = backgroundTertiary,
    iconsPrimary = iconsPrimary,
    iconsSecondary = iconsSecondary,
)

fun darkColors(
    textPrimary: Color = Color(0xFFEDEDEE),
    textSecondary: Color = Color(0xFF9C9BA2),
    textDisabled: Color = Color(0xFF3C3C41),
    backgroundLine: Color = Color(0xFF2B2A2E),
    backgroundPlaceholder: Color = Color(0xFF3C3C41),
    backgroundSkeleton: Color = Color(0xFF2B2A2E),
    backgroundPrimary: Color = Color(0xFF0D0D0F),
    backgroundSecondary: Color = Color(0xFF1B1A1E),
    backgroundTertiary: Color = Color(0xFF2B2A2E),
    iconsPrimary: Color = Color(0xFFEDEDEE),
    iconsSecondary: Color = Color(0xFF9C9BA2),
) = WeatherColors(
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    textDisabled = textDisabled,
    backgroundLine = backgroundLine,
    backgroundPlaceholder = backgroundPlaceholder,
    backgroundSkeleton = backgroundSkeleton,
    backgroundPrimary = backgroundPrimary,
    backgroundSecondary = backgroundSecondary,
    backgroundTertiary = backgroundTertiary,
    iconsPrimary = iconsPrimary,
    iconsSecondary = iconsSecondary,
)

data class WeatherColors(
    val textPrimary: Color,
    val textSecondary: Color,
    val textDisabled: Color,
    val backgroundLine: Color,
    val backgroundPlaceholder: Color,
    val backgroundSkeleton: Color,
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTertiary: Color,
    val iconsPrimary: Color,
    val iconsSecondary: Color,
)
