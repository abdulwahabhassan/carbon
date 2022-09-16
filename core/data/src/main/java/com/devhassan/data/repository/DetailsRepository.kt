package com.devhassan.data.repository

import com.devhassan.common.result.RepositoryResult
import com.devhassan.data.datasource.local.LocalDataSource
import com.devhassan.data.datasource.remote.RemoteDataSource
import com.devhassan.database.entity.LocalDetailsEntity
import com.devhassan.network.model.NetworkResult
import com.devhassan.network.util.NetworkRequestUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val remoteDatasource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkRequestUtil: NetworkRequestUtil,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun retrieveDetails(id: Long) = withContext(dispatcher) {
        when (
            val networkResult = networkRequestUtil.coroutineHandler(dispatcher) {
                remoteDatasource.fetchMovieDetails(id)
            }
        ) {
            is NetworkResult.Success -> {
                Timber.d("Repo Details -> ${networkResult.payload.toDomainModel()}")
                RepositoryResult.Remote(
                    data = networkResult.payload.toDomainModel()
                )
            }
            is NetworkResult.Error -> {
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