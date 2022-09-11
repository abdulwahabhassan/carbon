package com.devhassan.data.datasource

interface MoviesDataSrc {

    suspend fun fetchPopularMovies(): List<Any>

    suspend fun fetchTopRatedMovies(): List<Any>

    suspend fun fetchUpcomingMovies(): List<Any>

    suspend fun fetchNowPlayingMovies(): List<Any>

    suspend fun fetchMovieDetails(): List<Any>
}