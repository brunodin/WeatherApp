package com.weather.weatherapplication.domain.util


sealed class Resource<T>(
    val data: T? = null,
    val code: Int? = null,
    var message: String? = null,
    var error: String? = null,
    val throwable: Throwable? = null
) {
    class Success<T>(data: T?): Resource<T>(data)
    class Loading<T>(): Resource<T>()
    class Error<T>(code: Int, error: String): Resource<T>(code = code, error = error)
    class Failure<T>(throwable: Throwable): Resource<T>(throwable = throwable)
}
