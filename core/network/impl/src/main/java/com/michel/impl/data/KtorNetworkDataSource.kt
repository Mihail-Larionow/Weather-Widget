package com.michel.impl.data

import com.google.gson.Gson
import com.michel.api.data.NetworkDataSource
import com.michel.api.data.client.HttpClient
import com.michel.api.models.GetRequest
import com.michel.api.models.Request
import com.michel.api.models.Response
import com.michel.api.models.exceptions.TimeoutException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class KtorNetworkDataSource @Inject constructor(
    private val httpClient: HttpClient,
) : NetworkDataSource {

    override suspend fun <R> networkRequest(request: Request<R>): Result<R> = try {
        withContext(Dispatchers.IO) {
            delay(3000)
            performRequest(request).toResult(request.responseType)
        }
    } catch (delay: TimeoutCancellationException) {
        Result.failure(TimeoutException)
    } catch (exception: CancellationException) {
        throw exception
    } catch (error: Throwable) {
        Result.failure(error)
    }

    private suspend fun <R> performRequest(
        request: Request<R>,
    ): Response = when (request) {
        is GetRequest -> httpClient.get(
            url = request.url,
            headers = request.headers,
            queryParams = request.queryParams,
        )
    }

    private fun <R> Response.toResult(type: Class<R>): Result<R> =
        Result.success(Gson().fromJson(body, type))
}
