package com.michel.navigation.di

import com.michel.navigation.presentation.navigation.base.NavApi
import dagger.Component

@NavScope
@Component(
    modules = [
        NavApiModule::class,
        NavModule::class,
    ],
    dependencies = [
        NavDependencies::class
    ]
)
internal interface NavComponent : NavApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: NavDependencies,
        ): NavComponent
    }
}
