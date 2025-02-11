package com.michel.weatherit.main.di

import com.michel.weatherit.main.presentation.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface ActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>
}
