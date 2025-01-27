package com.michel.designsystem.composables.snackbar

import androidx.compose.material.SnackbarDuration

sealed interface SnackbarState {

    data object Hidden : SnackbarState

    data class Shown(
        val layout: SnackbarLayout,
        val duration: SnackbarDuration,
    ) : SnackbarState
}
