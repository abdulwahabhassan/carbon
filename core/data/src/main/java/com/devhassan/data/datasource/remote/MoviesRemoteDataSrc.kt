package com.devhassan.data.datasource.remote

import com.devhassan.data.api.MoviesApiService
import com.devhassan.data.datasource.MoviesDataSrc
import javax.inject.Inject

class MoviesRemoteDataSrc @Inject constructor(
    private val apiService: MoviesApiService
) : MoviesDataSrc {
    override suspend fun fetchPopularMovies(): List<Any> {
        return apiService.getPopularMovies()
    }

    override suspend fun fetchTopRatedMovies(): List<Any> {
        return apiService.getTopRatedMovies()
    }

    override suspend fun fetchUpcomingMovies(): List<Any> {
        return apiService.getUpcomingMovies()
    }

    override suspend fun fetchNowPlayingMovies(): List<Any> {
        return apiService.getNowPlayingMovies()
    }

    override suspend fun fetchMovieDetails(): List<Any> {
        return apiService.getMovieDetailsById()
    }

}