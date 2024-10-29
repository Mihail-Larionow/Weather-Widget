package com.michel.network.api.ktor

import android.util.Log
import com.michel.network.api.json
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.host
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.SerializationException
import java.util.concurrent.TimeoutException
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <reified T> HttpClient.safeRequest(
    host: String,
    path: String,
    params: StringValues = StringValues.Empty,
    method: HttpMethod = HttpMethod.Get,
    body: Any? = null,
): Result<T> = try {
    val response = request {
        this.method = method
        this.host = host
        url {
            protocol = URLProtocol.HTTPS
            path(path)
            parameters.appendAll(params)
        }
        contentType(ContentType.Application.Json)
        if (body != null) setBody(body)
    }
    val stringBody = response.body<String>()
    Log.i("WiApp Http", stringBody)
    if (response.status.isSuccess()) Result.success(
        json.decodeFromString<T>(stringBody)
    )
    else Result.failure(Exception())
} catch (exception: SerializationException) {
    exception.printStackTrace()
    Result.failure(SerializationException())
} catch (exception: TimeoutCancellationException) {
    Result.failure(TimeoutException())
} catch (exception: CancellationException) {
    throw exception
} catch (exception: Exception) {
    Result.failure(exception)
}