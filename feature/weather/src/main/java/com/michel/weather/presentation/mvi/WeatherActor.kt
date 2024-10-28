package com.michel.weather.presentation.mvi

import com.michel.mvi.Actor
import com.michel.weather.domain.GetWeatherDataUseCase
import com.michel.weather.domain.WeatherInitData
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherActor @Inject constructor(
    private val getWeatherDataUseCase: GetWeatherDataUseCase,
) : Actor<WeatherIntent, WeatherState, WeatherMessage> {

    override fun init(): Flow<WeatherMessage> = flow {
        val weatherData = getWeatherDataUseCase()
        val weatherInitData = WeatherInitData(
            weatherData = weatherData,
        )
        emit(
            WeatherMessage.WeatherDataLoaded(
                weatherInitData = weatherInitData,
            )
        )
    }

    override fun run(
        intent: WeatherIntent,
        prevState: WeatherState
    ): Flow<WeatherMessage> = flow {
        when(intent) {
            is WeatherIntent.CopyActionClicked -> emit(WeatherMessage.TextWasCopied)
        }
    }
}