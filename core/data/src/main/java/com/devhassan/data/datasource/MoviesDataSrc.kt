package com.devhassan.data.datasource


interface MoviesDataSrc<T, K> {

    suspend fun fetchMovies(category: String): T

    suspend fun fetchMovieDetails(id: Long): K?
}