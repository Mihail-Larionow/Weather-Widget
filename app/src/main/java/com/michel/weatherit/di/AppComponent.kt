package com.michel.weatherit.di

import android.content.Context
import com.michel.weatherit.WeatherApplication
import com.michel.weatherit.main.di.ActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ActivityModule::class,
        AndroidInjectionModule::class,
    ]
)
interface AppComponent : AndroidInjector<WeatherApplication> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
        ): AppComponent
    }
}
