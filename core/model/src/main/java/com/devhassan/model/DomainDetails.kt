package com.devhassan.model

data class DomainDetails (
    val adult: Boolean,
    val backdropPath: String,
    val budget: Long,
    val domainGenres: List<DomainGenre>,
    val homepage: String,
    val id: Long,
    val imdbID: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<DomainProductionCompany>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val domainSpokenLanguages: List<DomainSpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)

data class DomainGenre (
    val id: Long,
    val name: String
)

data class DomainProductionCompany (
    val id: Long,
    val logoPath: String? = null,
    val name: String,
    val originCountry: String
)


data class DomainSpokenLanguage (
    val englishName: String,
    val iso639_1: String,
    val name: String
)
