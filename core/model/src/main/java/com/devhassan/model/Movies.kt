package com.devhassan.model

data class MoviesResponse(
    val dates: Dates,
    val page: Long,
    val results: List<Movie>,
    val totalPages: Long,
    val totalResults: Long
)

data class Dates (
    val maximum: String,
    val minimum: String
)

data class Movie (
    val adult: Boolean,
    val backdropPath: String,
    val id: Long,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)