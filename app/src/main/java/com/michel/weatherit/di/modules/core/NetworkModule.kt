package com.michel.weatherit.di.modules.core

import com.michel.api.data.NetworkDataSource
import com.michel.impl.di.NetworkApi
import com.michel.impl.di.NetworkDependencies
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {

    @Provides
    fun provideNetworkDependencies(): NetworkDependencies = object : NetworkDependencies {

    }

    @Provides
    fun provideNetworkDataSource(networkApi: NetworkApi): NetworkDataSource = networkApi.dataSource
}
