package com.devhassan.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET(value = "movie/{category}")
    suspend fun getMovies(
        @Path("category", encoded = true) category: String,
        @Query("page") page: Int? = 1,
    ): com.devhassan.data.model.RemoteMoviesResponse

    @GET(value = "movie/{movie_id}")
    suspend fun getMovieDetailsById(
        @Path("movie_id") movieId: Long = 1,
    ): com.devhassan.data.model.RemoteDetailsResponse

}
