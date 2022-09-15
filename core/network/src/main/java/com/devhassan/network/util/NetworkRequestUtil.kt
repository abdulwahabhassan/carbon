package com.devhassan.network.util

import com.devhassan.network.manager.NetworkConnectivityManager
import com.devhassan.network.model.NetworkResult
import com.devhassan.network.model.RemoteErrorResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class NetworkRequestUtil @Inject constructor(
    val dispatcher: CoroutineDispatcher,
    private val networkConnectivityManager: NetworkConnectivityManager,
) {
    suspend fun <T> coroutineHandler(
        apiRequest: suspend () -> T
    ): NetworkResult<T> {
        return withContext(dispatcher) {
            if (!networkConnectivityManager.isNetworkAvailable()) {
                NetworkResult.Error("Check your internet connection!")
            } else {
                try {
                    NetworkResult.Success(apiRequest.invoke())
                } catch (e: HttpException) {
                    Timber.d("Http Error Http Status Code - ${e.code()} ")
                    val response = handleHttpException(e)
                    NetworkResult.Error(message = response?.statusMessage)
                } catch (e: Exception) {
                    Timber.d("Exception Error ${e.message}")
                    NetworkResult.Error(message = e.localizedMessage)
                }
            }
        }
    }

    private fun handleHttpException(e: HttpException): RemoteErrorResponse? {
        return try {
            e.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
                    .adapter(RemoteErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (t: Throwable) {
            Timber.d("Error while handling httpException $t")
            null
        }
    }
}