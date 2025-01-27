package com.michel.weather.presentation.mvi

import com.michel.mvi.store.Reducer
import com.michel.weather.navigation.WeatherNavDirection
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import io.kotest.matchers.shouldBe
import org.junit.Test

class WeatherReducerTest {

    private val weatherStateLoading = WeatherState.Loading
    private val weatherStateLoaded = WeatherState.Loaded(
        temperature = WeatherMocks.temperatures,
    )

    private val weatherReducer = WeatherReducer()

    @Test
    fun `when weather data loaded then reduce state`() {
        weatherReducer.reduce(
            message = WeatherMessage.WeatherDataLoaded(
                weatherData = WeatherMocks.fullyWeatherData,
            ),
            prevState = weatherStateLoading,
        ).shouldBe(weatherStateLoaded)
    }

    @Test
    fun `when navigate message is received then return navigation effect`() {
        weatherReducer.reduce(
            message = WeatherMessage.Navigate(WeatherNavDirection.ToProfile),
            prevState = weatherStateLoading,
        ).shouldBe(weatherStateLoading)
        weatherReducer.reduceEffect(
            message = WeatherMessage.Navigate(WeatherNavDirection.ToProfile),
        ).shouldBe(WeatherEffect.Navigate(WeatherNavDirection.ToProfile))
    }

    @Test
    fun `when any other message is received then return nothing`() {
        weatherReducer.reduceEffect(
            message = WeatherMessage.Empty,
        ).shouldBe(Reducer.nothing)
    }
}
