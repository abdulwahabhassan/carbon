package com.devhassan.movies.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhassan.common.result.RepositoryResult
import com.devhassan.common.result.ViewModelResult
import com.devhassan.data.repository.MoviesRepository
import com.devhassan.model.DomainMovie
import com.devhassan.movies.model.MoviesScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesScreenViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var moviesScreenUiState by mutableStateOf(
        MoviesScreenUiState(
            ViewModelResult.INITIAL,
            emptyList()
        )
    )

    init {
        getMovies("popular")
    }

    fun getMovies(category: String) {

        moviesScreenUiState = moviesScreenUiState.copy(viewModelResult = ViewModelResult.LOADING)

        viewModelScope.launch {
            when (val result = moviesRepository.retrieveMovies(category)) {
                is RepositoryResult.Error -> {
                    moviesScreenUiState = MoviesScreenUiState(
                        ViewModelResult.ERROR,
                        emptyList(),
                        result.message
                    )
                }
                is RepositoryResult.Local -> {
                    moviesScreenUiState = MoviesScreenUiState(
                        ViewModelResult.SUCCESS,
                        result.data,
                        "Showing cached results!"
                    )
                }
                is RepositoryResult.Remote -> {
                    moviesScreenUiState = MoviesScreenUiState(
                        ViewModelResult.SUCCESS,
                        result.data,
                        "Showing live results!"
                    )
                }
            }
        }
    }
}
