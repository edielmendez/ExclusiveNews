package com.mx.ediel.exclusivenews.data.remote.common

sealed class NetworkResult<out T>{
    class Success<T>(val data: T): NetworkResult<T>()
    class Error(val error: String): NetworkResult<Nothing>()
}
/*
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}
 */