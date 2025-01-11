package com.michel.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelFactoryProvider {

    @Suppress("UNCHECKED_CAST")
    fun provide(
        factory: () -> ViewModel
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = factory() as T
    }
}
