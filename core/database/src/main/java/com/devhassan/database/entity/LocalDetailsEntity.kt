package com.devhassan.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.devhassan.database.converter.Converter
import com.devhassan.model.*

@Entity(tableName = "details")
@TypeConverters(Converter::class)
data class LocalDetailsEntity(
    @PrimaryKey
    val id: Long,
    val adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    val budget: Long,
    val genres: List<GenreEntity>,
    val homepage: String,

    @ColumnInfo(name = "imdb_id")
    val imdbID: String,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "production_companies")
    val productionCompanies: List<ProductionCompanyEntity>,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @ColumnInfo(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageEntity>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    val voteCount: Long
) {
    fun toDataModel(): Details {
        return Details(
            this.adult,
            this.backdropPath,
            this.budget,
            this.genres.map { genreEntity -> genreEntity.toDataModel() },
            this.homepage,
            this.id,
            this.imdbID,
            this.originalLanguage,
            this.originalTitle,
            this.overview,
            this.popularity,
            this.posterPath,
            this.productionCompanies.map { productCompanyEntity ->
                productCompanyEntity.toDataModel()
            },
            this.releaseDate,
            this.revenue,
            this.runtime,
            this.spokenLanguages.map { spokenLanguageEntity ->
                spokenLanguageEntity.toDataModel()
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

data class GenreEntity(
    val id: Long,
    val name: String
) {
    fun toDataModel(): Genre {
        return Genre(this.id, this.name)
    }
}

data class ProductionCompanyEntity(
    val id: Long,

    @ColumnInfo(name = "logo_path")
    val logoPath: String? = null,

    val name: String,

    @ColumnInfo(name = "origin_country")
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

data class SpokenLanguageEntity(
    @ColumnInfo(name = "english_name")
    val englishName: String,

    @ColumnInfo(name = "iso_639_1")
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
