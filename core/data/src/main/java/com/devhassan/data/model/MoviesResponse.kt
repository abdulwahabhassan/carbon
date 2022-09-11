package com.devhassan.network.model

import com.devhassan.network.model.Dates
import com.devhassan.network.model.Movie
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