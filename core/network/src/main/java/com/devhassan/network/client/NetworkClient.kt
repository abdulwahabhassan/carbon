package com.devhassan.network.client

import com.devhassan.network.BuildConfig
import com.devhassan.network.model.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NetworkClient @Inject constructor (
    private val moshiConverterFactory: MoshiConverterFactory,
) {

    private val loggerInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val requestInterceptor by lazy {
        Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.MOVIES_API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder().apply {
            this
                .addInterceptor(requestInterceptor)
                .addInterceptor(loggerInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
        }.build()
    }

    inline fun <reified T>getApiService(api: Api): T {
        return getRetrofitInstance(api.baseUrl).create(T::class.java)
    }

    fun getRetrofitInstance(
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

}