package com.devhassan.data.repository

import com.devhassan.common.result.RepositoryResult
import com.devhassan.data.datasource.local.LocalDataSource
import com.devhassan.data.datasource.remote.RemoteDataSource
import com.devhassan.database.entity.LocalDetailsEntity
import com.devhassan.network.model.NetworkResult
import com.devhassan.network.util.NetworkRequestUtil
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val remoteDatasource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkRequestUtil: NetworkRequestUtil
) {

    suspend fun retrieveDetails(id: Long) = withContext(networkRequestUtil.dispatcher) {
        when (
            val networkResult = networkRequestUtil.coroutineHandler {
                remoteDatasource.fetchMovieDetails(id)
            }
        ) {
            is NetworkResult.Success -> {
                Timber.d("Success -> ${networkResult.payload}")
                RepositoryResult.Remote(
                    data = networkResult.payload.toDomainModel()
                )
            }
            is NetworkResult.Error -> {
                Timber.d("Error -> ${networkResult.message}")
                val localResult = localDataSource.fetchMovieDetails(id)

                if (localResult == null) {
                    RepositoryResult.Error(networkResult.message)
                } else {
                    RepositoryResult.Local(localResult.toDomainModel())
                }
            }
        }
    }

    suspend fun insertDetails(localDetailsEntity: LocalDetailsEntity) {
        localDataSource.insertDetails(localDetailsEntity)
    }


}