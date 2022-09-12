package com.devhassan.common.result

sealed class RepositoryResult<out T>() {

    data class Remote<out T>(val data: T) : RepositoryResult<T>()

    data class Local<out T>(val data: T) : RepositoryResult<T>()

    data class Error(val message: String?) : RepositoryResult<Nothing>()
}