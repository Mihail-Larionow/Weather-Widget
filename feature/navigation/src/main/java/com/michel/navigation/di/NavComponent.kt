package com.michel.navigation.di

import com.michel.navigation.presentation.navigation.NavApi
import dagger.Component

@NavScope
@Component(
    modules = [
        NavApiModule::class,
    ],
    dependencies = [
        NavDependencies::class
    ]
)
internal interface NavComponent : NavApi {

    fun getNavPresentationSubcomponent(): NavPresentationSubcomponent

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: NavDependencies,
        ): NavComponent
    }
}
