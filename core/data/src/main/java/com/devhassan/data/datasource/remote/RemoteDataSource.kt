package com.devhassan.data.datasource.remote

import com.devhassan.data.api.MoviesApiService
import com.devhassan.data.datasource.MoviesDataSrc
import com.devhassan.data.model.RemoteDetailsResponse
import com.devhassan.data.model.RemoteMoviesResponse
import javax.inject.Inject

class RemoteDataSrc @Inject constructor(
    private val apiService: MoviesApiService
) : MoviesDataSrc<RemoteMoviesResponse, RemoteDetailsResponse> {

    override suspend fun fetchMovies(category: String): RemoteMoviesResponse {
        return apiService.getMovies(category)
    }

    override suspend fun fetchMovieDetails(id: Long): RemoteDetailsResponse {
        return apiService.getMovieDetailsById(id)
    }

}