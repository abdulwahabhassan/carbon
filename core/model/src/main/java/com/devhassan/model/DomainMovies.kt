package com.devhassan.model

data class DomainMovies(
    val domainDates: DomainDates,
    val page: Long,
    val results: List<DomainMovie>,
    val totalPages: Long,
    val totalResults: Long
)

data class DomainDates (
    val maximum: String,
    val minimum: String
)

data class DomainMovie (
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