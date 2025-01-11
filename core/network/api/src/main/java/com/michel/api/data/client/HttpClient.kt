package com.michel.api.data.client

import com.michel.api.models.Response

interface HttpClient {

    suspend fun get(url: String, headers: Map<String, String>, queryParams: Map<String, String?>): Response
}
