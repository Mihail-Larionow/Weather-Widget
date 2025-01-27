package com.michel.designsystem.composables.snackbar

import androidx.compose.ui.graphics.painter.Painter

sealed interface SnackbarLayout {
    data class Toast(
        val text: String,
    ) : SnackbarLayout

    data class WithActionButton(
        val text: String,
        val icon: Painter,
        val buttonTitle: String,
        val onButtonClick: () -> Unit,
    ) : SnackbarLayout
}
