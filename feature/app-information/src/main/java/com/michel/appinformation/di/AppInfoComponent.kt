package com.michel.appinformation.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppInfoModule::class
    ],
    dependencies = [
        AppInfoDependencies::class,
    ],
)
internal interface AppInfoComponent : AppInfoApi, AppInfoInternalApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: AppInfoDependencies,
        ): AppInfoComponent
    }
}
