package com.devhassan.database.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devhassan.model.DomainMovie

@Entity(tableName = "movie")
data class LocalMovieEntity(
    @PrimaryKey
    val id: Long,
    val adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    val title: String,
    val video: Boolean,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    val voteCount: Long,

    @ColumnInfo(name = "category")
    val category: String
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
            this.posterPath,
            this.releaseDate,
            this.title,
            this.video,
            this.voteAverage,
            this.voteCount
        )

    }
}
