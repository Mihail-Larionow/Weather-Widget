package com.michel.weather.presentation.mvi

import com.michel.mvi.Reducer
import com.michel.weather.presentation.mvi.entities.WeatherScreenEffect
import com.michel.weather.presentation.mvi.entities.WeatherScreenMessage
import com.michel.weather.presentation.mvi.entities.WeatherScreenState
import javax.inject.Inject

class WeatherScreenReducer @Inject constructor(

) : Reducer<WeatherScreenEffect, WeatherScreenState, WeatherScreenMessage> {

    override fun reduce(
        message: WeatherScreenMessage,
        prevState: WeatherScreenState
    ): WeatherScreenState {
        TODO("Not yet implemented")
    }

    override fun reduceEffect(message: WeatherScreenMessage): WeatherScreenEffect? {
        TODO("Not yet implemented")
    }
}