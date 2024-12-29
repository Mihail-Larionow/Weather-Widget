package com.michel.navigation.presentation.mvi

import com.michel.mvi.store.Actor
import com.michel.navigation.presentation.mvi.entities.NavIntent
import com.michel.navigation.presentation.mvi.entities.NavMessage
import com.michel.navigation.presentation.mvi.entities.NavState
import com.michel.navigation.presentation.navigation.MainNavDirection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class NavActor @Inject constructor() : Actor<NavIntent, NavState, NavMessage> {
    override fun run(intent: NavIntent, prevState: NavState): Flow<NavMessage> = when (intent) {
        NavIntent.NavigateUp -> flowOf(NavMessage.Navigate(MainNavDirection.Up))
    }
}
