package com.devhassan.data.model

import com.squareup.moshi.Json

data class MoviesResponse(
    val dates: Dates,
    val page: Long,
    val results: List<Movie>,

    @Json(name = "total_pages")
    val totalPages: Long,

    @Json(name = "total_results")
    val totalResults: Long
)

data class Dates (
    val maximum: String,
    val minimum: String
)

data class Movie (
    val adult: Boolean,

    @Json(name = "backdrop_path")
    val backdropPath: String,

    @Json(name = "genre_ids")
    val genreIDS: List<Long>,

    val id: Long,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @Json(name = "poster_path")
    val posterPath: String,

    @Json(name = "release_date")
    val releaseDate: String,

    val title: String,
    val video: Boolean,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "vote_count")
    val voteCount: Long
)