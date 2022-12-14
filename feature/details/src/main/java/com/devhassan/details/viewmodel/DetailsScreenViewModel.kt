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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    var detailsScreenUiState by mutableStateOf(
        DetailsScreenUiState(
            ViewModelResult.INITIAL,
            null
        )
    )

    fun getMovieDetails(id: Long) {

        detailsScreenUiState = detailsScreenUiState.copy(viewModelResult = ViewModelResult.LOADING)

        viewModelScope.launch {
            when (val result = detailsRepository.retrieveDetails(id)) {
                is RepositoryResult.Error -> {
                    Timber.d("Vm Remote Error Details -> ${result.message}")
                    detailsScreenUiState = DetailsScreenUiState(
                        ViewModelResult.ERROR,
                        null,
                        result.message
                    )
                }
                is RepositoryResult.Local -> {
                    Timber.d("Vm Remote Local Details -> ${result.data}")
                    detailsScreenUiState = DetailsScreenUiState(
                        ViewModelResult.SUCCESS,
                        result.data,
                        "Showing cached movies!"
                    )
                }
                is RepositoryResult.Remote -> {
                    Timber.d("Vm Remote Success Details -> ${result.data}")
                    detailsScreenUiState = DetailsScreenUiState(
                        ViewModelResult.SUCCESS,
                        result.data
                    )
                }
            }
        }
    }
}
