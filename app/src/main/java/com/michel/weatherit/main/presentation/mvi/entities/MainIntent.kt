package com.michel.weatherit.main.presentation.mvi.entities

import android.content.Intent

sealed interface MainIntent {
    data class HandleSystemIntent(val systemIntent: Intent) : MainIntent
}
