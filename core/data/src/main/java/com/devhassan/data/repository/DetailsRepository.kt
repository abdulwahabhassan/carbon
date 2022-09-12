package com.devhassan.data.repository

import com.devhassan.common.result.NetworkResult
import com.devhassan.common.result.RepositoryResult
import com.devhassan.data.datasource.local.LocalDataSource
import com.devhassan.data.datasource.remote.RemoteDataSource
import com.devhassan.database.entity.LocalDetailsEntity
import com.devhassan.database.entity.LocalMovieEntity
import com.devhassan.network.manager.NetworkConnectivityManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val remoteDatasource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkConnectivityManager: NetworkConnectivityManager,
    private val dispatcher: CoroutineDispatcher
) : BaseRepository() {

    suspend fun retrieveDetails(id: Long) = withContext(dispatcher) {
        when (
            val networkResult = coroutineHandler(dispatcher, networkConnectivityManager) {
                remoteDatasource.fetchMovieDetails(id)
            }
        ) {
            is NetworkResult.Success -> {
                Timber.d("Success -> ${networkResult.payload}")
                RepositoryResult.Remote(
                    data = networkResult.payload.toDataModel()
                )
            }
            is NetworkResult.Error -> {
                Timber.d("Error -> ${networkResult.message}")
                val localResult = localDataSource.fetchMovieDetails(id)

                if (localResult == null) {
                    RepositoryResult.Error(networkResult.message)
                } else {
                    RepositoryResult.Local(localResult.toDataModel())
                }
            }
        }
    }

    suspend fun insertDetails(localDetailsEntity: LocalDetailsEntity) {
        localDataSource.insertDetails(localDetailsEntity)
    }


}