package com.devhassan.data.repository

import com.devhassan.common.result.NetworkResult
import com.devhassan.common.result.RepositoryResult
import com.devhassan.data.datasource.local.LocalDataSource
import com.devhassan.data.datasource.remote.RemoteDataSource
import com.devhassan.database.entity.LocalMovieEntity
import com.devhassan.network.manager.NetworkConnectivityManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDatasource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkConnectivityManager: NetworkConnectivityManager,
    private val dispatcher: CoroutineDispatcher
) : BaseRepository() {

    suspend fun retrieveMovies(category: String) = withContext(dispatcher) {
        when (
            val networkResult = coroutineHandler(dispatcher, networkConnectivityManager) {
                remoteDatasource.fetchMovies(category)
            }
        ) {
            is NetworkResult.Success -> {
                Timber.d("Success -> ${networkResult.payload}")
                RepositoryResult.Remote(
                    data = networkResult.payload.results.map { remoteMovie ->
                        remoteMovie.toDomainModel()
                    }
                )
            }
            is NetworkResult.Error -> {
                Timber.d("Error -> ${networkResult.message}")
                val localResult = localDataSource.fetchMovies(category)
                if (localResult.isEmpty()) {
                    RepositoryResult.Error(networkResult.message)
                } else {
                    RepositoryResult.Local(localResult.map { localMovieEntity ->
                        localMovieEntity.toDomainModel()
                    })
                }
            }
        }
    }

    suspend fun insertMovie(localMovieEntity: LocalMovieEntity) {
        localDataSource.insertMovie(localMovieEntity)
    }

}
