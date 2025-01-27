package com.michel.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.impl.Nav
import com.michel.navigation.di.NavComponentHolder
import com.michel.navigation.di.NavPresentationSubcomponent


private const val PRESENTATION_SUBCOMPONENT_DATA = 0

@Composable
fun NavigationHost() {
    val subcomponent = remember {
        NavComponentHolder
            .getSubcomponent<NavPresentationSubcomponent>(PRESENTATION_SUBCOMPONENT_DATA)
    }

    Nav(
        viewModel = viewModel<MainNavViewModel> { subcomponent.viewModelProvider.get() },
        destinationRegistrar = remember { subcomponent.registrar },
    )
}
