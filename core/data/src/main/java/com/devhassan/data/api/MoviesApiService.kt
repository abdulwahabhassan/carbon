package com.devhassan.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET(value = "movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("page") page: Int,
    ): com.devhassan.data.model.RemoteMoviesResponse

    @GET(value = "movie/{movie_id}")
    suspend fun getMovieDetailsById(
        @Path("movie_id") movieId: Long,
    ): com.devhassan.data.model.RemoteDetailsResponse

}
