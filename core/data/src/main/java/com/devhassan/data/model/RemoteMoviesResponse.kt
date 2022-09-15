package com.devhassan.data.model

import com.devhassan.model.DomainMovie
import com.squareup.moshi.Json

data class RemoteMoviesResponse(
    val page: Long,
    val results: List<RemoteMovie>,

    @Json(name = "total_pages")
    val totalPages: Long,

    @Json(name = "total_results")
    val totalResults: Long
)

data class RemoteMovie(
    val adult: Boolean,

    @Json(name = "backdrop_path")
    val backdropPath: String,

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
) {
    fun toDomainModel(): DomainMovie {
        return DomainMovie(
            this.adult,
            this.backdropPath,
            this.id,
            this.originalLanguage,
            this.originalTitle,
            this.overview,
            this.popularity,
            "https://image.tmdb.org/t/p/original" + this.posterPath,
            this.releaseDate,
            this.title,
            this.video,
            this.voteAverage,
            this.voteCount
        )
    }

}