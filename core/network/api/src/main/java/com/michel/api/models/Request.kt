package com.michel.api.models


sealed class Request<R> {
    abstract val url: String
    abstract val headers: Map<String, String>
    abstract val responseType: Class<R>
}

open class GetRequest<R>(
    override val url: String,
    override val responseType: Class<R>,
    override val headers: Map<String, String> = mapOf(),
    val queryParams: Map<String, String?> = mapOf(),
) : Request<R>()
