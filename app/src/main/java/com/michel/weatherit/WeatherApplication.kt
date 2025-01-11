package com.michel.weatherit

import com.michel.weatherit.di.DaggerAppComponent
import com.michel.weatherit.di.initializers.ComponentHolderInitializer
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

open class WeatherApplication : DaggerApplication() {

    @Inject
    lateinit var componentHolderInitializer: ComponentHolderInitializer

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(applicationContext = this)

    override fun onCreate() {
        super.onCreate()
        initComponents()
    }

    protected open fun initComponents() {
        componentHolderInitializer.initAll()
    }
}
