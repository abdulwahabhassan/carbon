package com.devhassan.details.model

import com.devhassan.common.result.ViewModelResult
import com.devhassan.model.DomainDetails
import com.devhassan.model.DomainMovie
import com.devhassan.model.DomainMovies

data class DetailsScreenUiState(
    val viewModelResult: ViewModelResult,
    val movieDetails: DomainDetails?,
    val message: String? = null
)