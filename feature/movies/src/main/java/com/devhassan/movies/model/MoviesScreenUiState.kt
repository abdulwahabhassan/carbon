package com.devhassan.movies.model

import androidx.paging.PagingData
import com.devhassan.common.result.ViewModelResult
import com.devhassan.model.DomainMovie
import com.devhassan.model.DomainMovies
import kotlinx.coroutines.flow.Flow

data class MoviesScreenUiState(
    val viewModelResult: ViewModelResult,
    val movies: Flow<PagingData<DomainMovie>>? = null,
    val message: String? = null,
    val category: String? = null
)