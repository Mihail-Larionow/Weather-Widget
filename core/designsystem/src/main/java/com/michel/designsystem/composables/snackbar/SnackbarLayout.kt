package com.michel.designsystem.composables.snackbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed interface SnackbarLayout {
    data class Toast(
        @StringRes val textResId: Int,
    ) : SnackbarLayout

    data class ErrorWithButton(
        @StringRes val textResId: Int,
        @StringRes val buttonTitleResId: Int,
        val onButtonClick: () -> Unit,
    ) : SnackbarLayout
}
