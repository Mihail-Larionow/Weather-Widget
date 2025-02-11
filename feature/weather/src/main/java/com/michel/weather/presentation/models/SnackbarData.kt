package com.michel.weather.presentation.models

import androidx.annotation.StringRes

data class SnackbarData(
    @StringRes val textResId: Int,
    val button: Button,
) {
    data class Button(
        @StringRes val titleResId: Int,
        val action: SnackbarAction,
    )

    companion object
}