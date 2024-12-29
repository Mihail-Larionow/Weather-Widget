package com.michel.ui.composables.bottomsheet

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class BottomSheetCornerStyle(val topRadius: Dp, val bottomRadius: Dp) {

    data object StandardCornerStyle : BottomSheetCornerStyle(32.dp, 0.dp)
}
