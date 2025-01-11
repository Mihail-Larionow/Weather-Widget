package com.michel.weather.presentation.mvi

import app.cash.turbine.test
import com.michel.unit.turbine.runTurbineTest
import com.michel.weather.domain.usecases.GetWeatherStateUseCase
import com.michel.weather.domain.usecases.ReloadWeatherDataUseCase
import com.michel.weather.navigation.WeatherNavDirection
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.Test

class WeatherActorTest {

    private val weatherActor = WeatherActor(
        getWeatherStateUseCase = GetWeatherStateUseCase(
            weatherDataRepository = WeatherMocks.weatherDataRepository,
        ),
        reloadWeatherDataUseCase = ReloadWeatherDataUseCase(
            weatherDataRepository = WeatherMocks.weatherDataRepository,
        ),
    )

    @Test
    fun `when weather data is present then return data loaded message`() = runTurbineTest {
        WeatherMocks.weatherData = WeatherMocks.fullyWeatherData
        weatherActor.init().test {
            val message = awaitItem()
            message.shouldBeInstanceOf<WeatherMessage.WeatherDataLoaded>()
            message.weatherData.shouldBe(WeatherMocks.weatherData)
        }
    }

    @Test
    fun `when profile clicked then return navigation message`() = runTurbineTest {
        weatherActor.run(
            intent = WeatherIntent.ProfileClicked,
            prevState = WeatherState.Loading,
        ).test {
            val message = awaitItem()
            message.shouldBeInstanceOf<WeatherMessage.Navigate>()
            message.direction.shouldBe(WeatherNavDirection.ToProfile)
            awaitComplete()
        }
    }

    @Test
    fun `when settings clicked then return navigation message`() = runTurbineTest {
        weatherActor.run(
            intent = WeatherIntent.SettingsClicked,
            prevState = WeatherState.Loading,
        ).test {
            val message = awaitItem()
            message.shouldBeInstanceOf<WeatherMessage.Navigate>()
            message.direction.shouldBe(WeatherNavDirection.ToSettings)
            awaitComplete()
        }
    }
}
