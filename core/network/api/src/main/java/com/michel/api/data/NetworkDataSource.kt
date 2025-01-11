package com.michel.api.data

import com.michel.api.models.Request

interface NetworkDataSource {

    suspend fun <R> networkRequest(
        request: Request<R>,
    ): Result<R>
}
