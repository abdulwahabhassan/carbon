package com.devhassan.network.model

sealed class NetworkResult<out T> {

    data class Success<out T>(val payload: T) : NetworkResult<T>()

    data class Error(val message: String? = null) : NetworkResult<Nothing>()
}