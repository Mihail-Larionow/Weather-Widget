package com.michel.settings.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SettingsModule::class
    ],
    dependencies = [
        SettingsDependencies::class,
    ],
)
internal interface SettingsComponent : SettingsApi, SettingsInternalApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: SettingsDependencies,
        ): SettingsComponent
    }
}
