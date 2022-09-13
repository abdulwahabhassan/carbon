package com.devhassan.details.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhassan.common.result.RepositoryResult
import com.devhassan.common.result.ViewModelResult
import com.devhassan.data.repository.DetailsRepository
import com.devhassan.data.repository.MoviesRepository
import com.devhassan.details.model.DetailsScreenUiState
import com.devhassan.model.DomainDetails
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    var detailsScreenUiState by mutableStateOf(
        DetailsScreenUiState(
            ViewModelResult.INITIAL,
            null
        )
    )

    fun getMovieDetails(id: Long) {
        viewModelScope.launch {
            when (val result = detailsRepository.retrieveDetails(id)) {
                is RepositoryResult.Error -> {
                    detailsScreenUiState = DetailsScreenUiState(
                        ViewModelResult.ERROR,
                        null,
                        result.message
                    )
                }
                is RepositoryResult.Local -> {
                    detailsScreenUiState = DetailsScreenUiState(
                        ViewModelResult.SUCCESS,
                        result.data,
                        "Showing cached movies!"
                    )
                }
                is RepositoryResult.Remote -> {
                    detailsScreenUiState = DetailsScreenUiState(
                        ViewModelResult.SUCCESS,
                        result.data
                    )
                }
            }
        }
    }
}
