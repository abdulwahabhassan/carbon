package com.devhassan.data.model

import com.devhassan.model.*
import com.squareup.moshi.Json


data class RemoteDetailsResponse(
    val adult: Boolean,

    @Json(name = "backdrop_path")
    val backdropPath: String,

    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any? = null,

    val budget: Long,
    val genres: List<RemoteGenre>,
    val homepage: String,
    val id: Long,

    @Json(name = "imdb_id")
    val imdbID: String,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @Json(name = "poster_path")
    val posterPath: String,

    @Json(name = "production_companies")
    val productionCompanies: List<RemoteProductionCompany>,

    @Json(name = "release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @Json(name = "spoken_languages")
    val spokenLanguages: List<RemoteSpokenLanguage>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "vote_count")
    val voteCount: Long
) {
    fun toDataModel(): Details {
        return Details(
            this.adult,
            this.backdropPath,
            this.budget,
            this.genres.map { remoteGenre -> remoteGenre.toDataModel() },
            this.homepage,
            this.id,
            this.imdbID,
            this.originalLanguage,
            this.originalTitle,
            this.overview,
            this.popularity,
            this.posterPath,
            this.productionCompanies.map { remoteProductionCompany ->
                remoteProductionCompany.toDataModel()
            },
            this.releaseDate,
            this.revenue,
            this.runtime,
            this.spokenLanguages.map { remoteSpokenLanguage ->
                remoteSpokenLanguage.toDataModel()
            },
            this.status,
            this.tagline,
            this.originalTitle,
            this.video,
            this.voteAverage,
            this.voteCount
        )
    }
}

data class RemoteGenre(
    val id: Long,
    val name: String
) {
    fun toDataModel(): Genre {
        return Genre(this.id, this.name)
    }
}

data class RemoteProductionCompany(
    val id: Long,

    @Json(name = "logo_path")
    val logoPath: String? = null,

    val name: String,

    @Json(name = "origin_country")
    val originCountry: String
) {
    fun toDataModel(): ProductionCompany {
        return ProductionCompany(
            this.id,
            this.logoPath,
            this.name,
            this.originCountry
        )
    }
}


data class RemoteSpokenLanguage(
    @Json(name = "english_name")
    val englishName: String,

    @Json(name = "iso_639_1")
    val iso639_1: String,

    val name: String
) {
    fun toDataModel(): SpokenLanguage {
        return SpokenLanguage(
            this.englishName,
            this.iso639_1,
            this.name
        )
    }
}
