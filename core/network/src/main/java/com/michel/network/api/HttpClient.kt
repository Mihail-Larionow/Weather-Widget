package com.michel.network.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val client by lazy {
    HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(json)
        }
        install(HttpTimeout) {
            connectTimeoutMillis = 10000L
            requestTimeoutMillis = 10000L
            socketTimeoutMillis = 10000L
        }
    }
}

val json by lazy {
    Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
}