package com.michel.impl.data.client

import com.michel.api.data.client.HttpClient
import com.michel.api.models.Response
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import javax.inject.Inject
import io.ktor.client.HttpClient as KtorHttpClient

class HttpClientImpl @Inject constructor(
    private val ktorHttpClient: KtorHttpClient
) : HttpClient {

    override suspend fun get(
        url: String,
        headers: Map<String, String>,
        queryParams: Map<String, String?>
    ): Response = ktorHttpClient.get(url) {
        headers.forEach { (key, value) -> header(key, value) }
        queryParams.forEach { (key, value) -> parameter(key, value) }
    }.toResponse()

    private suspend fun HttpResponse.toResponse(): Response = Response(
        url = request.url.toString(),
        body = this.bodyAsText()
    )
}
