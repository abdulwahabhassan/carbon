package com.devhassan.data.datasource

import com.devhassan.model.Details
import com.devhassan.model.Movie


interface MoviesDataSrc<T, K> {

    suspend fun fetchMovies(category: String): T

    suspend fun fetchMovieDetails(id: Long): K?
}