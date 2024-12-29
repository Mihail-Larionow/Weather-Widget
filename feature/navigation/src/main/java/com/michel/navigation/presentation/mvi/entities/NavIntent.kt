package com.michel.navigation.presentation.mvi.entities

sealed interface NavIntent {
    data object NavigateUp : NavIntent
}
