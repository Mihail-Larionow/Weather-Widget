package com.michel.weather.presentation.mvi

import com.michel.mvi.store.Actor
import com.michel.weather.domain.usecases.GetWeatherStateUseCase
import com.michel.weather.domain.usecases.ReloadWeatherDataUseCase
import com.michel.weather.navigation.WeatherNavDirection
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class WeatherActor @Inject constructor(
    private val getWeatherStateUseCase: GetWeatherStateUseCase,
    private val reloadWeatherDataUseCase: ReloadWeatherDataUseCase,
) : Actor<WeatherIntent, WeatherState, WeatherMessage> {

    override fun init(): Flow<WeatherMessage> = merge(
        subscribeOnWeatherState(),
        reloadWeatherData()
    )

    override fun run(
        intent: WeatherIntent,
        prevState: WeatherState
    ): Flow<WeatherMessage> = when (intent) {
        WeatherIntent.Reload -> reloadWeatherData()
        WeatherIntent.ProfileClicked -> flowOf(WeatherMessage.Navigate(WeatherNavDirection.ToProfile))
        WeatherIntent.SettingsClicked -> flowOf(WeatherMessage.Navigate(WeatherNavDirection.ToSettings))
    }

    private fun subscribeOnWeatherState(): Flow<WeatherMessage> =
        getWeatherStateUseCase().map { weatherData ->
            WeatherMessage.WeatherDataLoaded(weatherData)
        }

    private fun reloadWeatherData(): Flow<WeatherMessage> = flow {
        reloadWeatherDataUseCase()
    }
}
