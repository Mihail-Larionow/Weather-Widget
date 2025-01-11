package com.michel.api.models.exceptions

object TimeoutException : NetworkException() {
    private fun readResolve(): Any = TimeoutException
}
