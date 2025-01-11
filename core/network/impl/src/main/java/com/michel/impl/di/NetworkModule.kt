package com.michel.impl.di

import com.michel.api.data.NetworkDataSource
import com.michel.api.data.client.HttpClient
import com.michel.impl.data.KtorNetworkDataSource
import com.michel.impl.data.client.HttpClientImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.HttpClient as KtorHttpClient

@Module
internal interface NetworkModule {

    @Binds
    fun bindHttpClient(
        httpClient: HttpClientImpl,
    ): HttpClient

    @Binds
    fun bindNetworkDataSource(
        dataSource: KtorNetworkDataSource,
    ): NetworkDataSource

    companion object {
        @Provides
        fun provideKtorHttpClient(): KtorHttpClient = KtorHttpClient {
            install(Logging) {

            }
        }
    }
}
