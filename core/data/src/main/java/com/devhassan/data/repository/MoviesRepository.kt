package com.devhassan.data.repository

import androidx.paging.map
import com.devhassan.common.result.RepositoryResult
import com.devhassan.data.datasource.local.LocalDataSource
import com.devhassan.data.datasource.remote.RemoteDataSource
import com.devhassan.database.entity.LocalMovieEntity
import com.devhassan.network.model.NetworkResult
import com.devhassan.network.util.NetworkRequestUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDatasource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkRequestUtil: NetworkRequestUtil,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun retrieveMovies(category: String) = withContext(dispatcher) {
        when (
            val networkResult = networkRequestUtil.coroutineHandler(dispatcher) {
                remoteDatasource.fetchMovies(category)
            }
        ) {
            is NetworkResult.Success -> {
                Timber.d("Success -> ${networkResult.payload}")
                RepositoryResult.Remote(
                    data = networkResult.payload.map {
                        it.map { remoteMovie ->
                            remoteMovie.toDomainModel()
                        }
                    }
                )
            }
            is NetworkResult.Error -> {
                Timber.d("Error -> ${networkResult.message}")
                RepositoryResult.Error(networkResult.message)
            }
        }
    }

    suspend fun insertMovie(localMovieEntity: LocalMovieEntity) {
        localDataSource.insertMovie(localMovieEntity)
    }

}

