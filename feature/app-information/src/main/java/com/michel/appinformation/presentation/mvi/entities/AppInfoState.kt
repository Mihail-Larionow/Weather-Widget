package com.michel.appinformation.presentation.mvi.entities

sealed interface AppInfoState {
    data object Loading : AppInfoState
}
