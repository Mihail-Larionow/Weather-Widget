package com.michel.impl.di

import dagger.Component

@Component(
    modules = [
        NetworkModule::class
    ],
    dependencies = [
        NetworkDependencies::class
    ]
)
internal interface NetworkComponent : NetworkApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: NetworkDependencies): NetworkComponent
    }
}
