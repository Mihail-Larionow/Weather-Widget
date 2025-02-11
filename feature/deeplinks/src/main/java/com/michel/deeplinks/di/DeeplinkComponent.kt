package com.michel.deeplinks.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DeeplinkModule::class
    ],
    dependencies = [
        DeeplinkDependencies::class,
    ],
)
internal interface DeeplinkComponent : DeeplinkApi, DeeplinkInternalApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: DeeplinkDependencies,
        ): DeeplinkComponent
    }
}
