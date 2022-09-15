package com.devhassan.data.datasource.remote

import androidx.paging.*
import com.devhassan.data.Constants
import com.devhassan.data.api.MoviesApiService
import com.devhassan.data.datasource.MoviesDataSrc
import com.devhassan.data.datasource.paging.MoviesPagingSource
import com.devhassan.data.model.RemoteDetailsResponse
import com.devhassan.data.model.RemoteMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: MoviesApiService
) : MoviesDataSrc<Flow<PagingData<RemoteMovie>>, RemoteDetailsResponse> {

    override suspend fun fetchMovies(category: String): Flow<PagingData<RemoteMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(apiService, category) }
        ).flow
    }

    override suspend fun fetchMovieDetails(id: Long): RemoteDetailsResponse {
        return apiService.getMovieDetailsById(id)
    }


}