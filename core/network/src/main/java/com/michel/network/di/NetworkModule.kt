package com.michel.network.di

import com.michel.network.data.WeatherNetworkDataSource
import com.michel.network.data.WeatherNetworkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {

    @Binds
    fun bindNetworkDataSource(
        weatherNetworkDataSourceImpl: WeatherNetworkDataSourceImpl,
    ): WeatherNetworkDataSource
}
