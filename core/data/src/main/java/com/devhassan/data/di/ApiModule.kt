package com.devhassan.data.di


import com.devhassan.data.api.MoviesApiService
import com.devhassan.network.client.NetworkClient
import com.devhassan.network.model.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesMoviesApiService(
        networkClient: NetworkClient
    ): MoviesApiService {
        return networkClient.getApiService(Api.Prod)
    }

}
