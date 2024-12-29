package com.michel.profile.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ProfileModule::class
    ],
    dependencies = [
        ProfileDependencies::class,
    ],
)
internal interface ProfileComponent : ProfileApi, ProfileInternalApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: ProfileDependencies,
        ): ProfileComponent
    }
}
