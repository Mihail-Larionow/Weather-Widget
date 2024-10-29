package com.michel.network.di

import com.michel.network.data.WeatherNetworkDataSource
import com.michel.network.data.WeatherNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CoreNetworkModule {

    @Binds
    fun bindNetworkDataSource(
        weatherNetworkDataSourceImpl: WeatherNetworkDataSourceImpl,
    ) : WeatherNetworkDataSource
}