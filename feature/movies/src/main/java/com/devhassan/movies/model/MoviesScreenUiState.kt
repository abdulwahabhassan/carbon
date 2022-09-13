package com.devhassan.movies.model

import com.devhassan.common.result.ViewModelResult
import com.devhassan.model.DomainMovie
import com.devhassan.model.DomainMovies

data class MoviesScreenUiState(
    val viewModelResult: ViewModelResult,
    val movies: List<DomainMovie>,
    val message: String? = null
)