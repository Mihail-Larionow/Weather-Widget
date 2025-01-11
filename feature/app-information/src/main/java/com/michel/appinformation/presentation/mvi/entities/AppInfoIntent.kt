package com.michel.appinformation.presentation.mvi.entities

sealed interface AppInfoIntent {
    data object BackClicked : AppInfoIntent
}
