package com.devhassan.network.di

import android.content.Context
import com.devhassan.network.manager.NetworkConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkManager(
        @ApplicationContext appContext: Context
    ): NetworkConnectivityManager {
        return NetworkConnectivityManager(appContext)
    }

}
