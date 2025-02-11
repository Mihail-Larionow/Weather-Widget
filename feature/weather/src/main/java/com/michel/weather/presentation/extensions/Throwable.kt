package com.michel.weather.presentation.extensions

import com.michel.weather.presentation.models.SnackbarAction
import com.michel.designsystem.R.string as stringsR

internal fun Throwable.toTextResId(): Int = when (this) {
    else -> stringsR.common_strings_unknown_exception_text
}

internal fun Throwable.toButtonTitleResId(): Int = when (this) {
    else -> stringsR.feature_weather_snackbar_button_reload_title
}

internal fun Throwable.toButtonAction(): SnackbarAction = when (this) {
    else -> SnackbarAction.RELOAD_WEATHER_DATA
}