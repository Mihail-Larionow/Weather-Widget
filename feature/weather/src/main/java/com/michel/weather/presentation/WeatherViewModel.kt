package com.michel.weather.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.michel.mvi.BaseViewModel
import com.michel.mvi.Store
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
internal class WeatherViewModel @Inject constructor(
    private val store: Store<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage>,
) : BaseViewModel<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage>(){

    private val scope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        Log.e("WiApp", "${throwable.message}")
    }

    override val state: StateFlow<WeatherState> by lazy(LazyThreadSafetyMode.NONE) {
        store.stateIn(scope, SharingStarted.WhileSubscribed(5000), store.state)
    }

    override val effect: Flow<WeatherEffect> by lazy(LazyThreadSafetyMode.NONE) {
        store.effects.receiveAsFlow()
    }

    override fun accept(intent: WeatherIntent) {
        scope.launch {
            store.emit(intent)
        }
    }

}