package com.michel.weather.presentation.mvi

import com.michel.mvi.Actor
import com.michel.weather.domain.GetWeatherDataUseCase
import com.michel.weather.presentation.mvi.entities.WeatherScreenIntent
import com.michel.weather.presentation.mvi.entities.WeatherScreenMessage
import com.michel.weather.presentation.mvi.entities.WeatherScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherScreenActor @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
) : Actor<WeatherScreenIntent, WeatherScreenMessage, WeatherScreenState> {

    override fun init(): Flow<WeatherScreenMessage> = flow {
        val weatherData = getWeatherDataUseCase()
    }

    override fun run(
        intent: WeatherScreenIntent,
        prevState: WeatherScreenState
    ): Flow<WeatherScreenMessage> {
        TODO("Not yet implemented")
    }
}