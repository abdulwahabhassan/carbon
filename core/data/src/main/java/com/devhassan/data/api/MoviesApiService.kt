package com.devhassan.network.api

import com.devhassan.network.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET(value = "movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?,
        @Query("language") language: String?,
        @Query("region") region: String?
    ): MoviesResponse

    @GET(value = "movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?,
        @Query("language") language: String?,
        @Query("region") region: String?
    ): MoviesResponse

    @GET(value = "movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?,
        @Query("language") language: String?,
        @Query("region") region: String?
    ): MoviesResponse

    @GET(value = "movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?,
        @Query("language") language: String?,
        @Query("region") region: String?
    ): MoviesResponse

    @GET(value = "movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("append_to_response") appendToResponse: String?
    ): MovieDetails

}
