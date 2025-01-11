package com.michel.designsystem.composables.buttons.icon

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class IconButtonSize(val value: Dp) {
    Small(24.dp),
    Medium(32.dp),
    Large(40.dp),
}

fun IconButtonSize.getActionButtonCornerSize(): Dp {
    return when (this) {
        IconButtonSize.Small -> 8.dp
        IconButtonSize.Medium -> 12.dp
        IconButtonSize.Large -> 16.dp
    }
}
