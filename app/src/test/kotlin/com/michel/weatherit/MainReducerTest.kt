package com.michel.weatherit

import com.michel.mvi.store.Reducer
import com.michel.weatherit.main.mvi.MainReducer
import com.michel.weatherit.main.mvi.entities.MainEffect
import com.michel.weatherit.main.mvi.entities.MainMessage
import com.michel.weatherit.main.mvi.entities.MainState
import io.kotest.matchers.shouldBe
import org.junit.Test

class MainReducerTest {

    private val mainLoadingState = MainState.Loading

    private val mainReducer = MainReducer()

    @Test
    fun `when finish splash message is received then return finish splash effect`() {
        mainReducer.reduce(
            message = MainMessage.FinishSplash,
            prevState = mainLoadingState,
        ).shouldBe(mainLoadingState)
        mainReducer.reduceEffect(
            message = MainMessage.FinishSplash,
        ).shouldBe(MainEffect.FinishSplash)
    }

    @Test
    fun `when any other message is received then return nothing`() {
        mainReducer.reduceEffect(
            message = MainMessage.Empty,
        ).shouldBe(Reducer.nothing)
    }
}
