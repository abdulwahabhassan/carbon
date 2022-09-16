package com.devhassan.movies.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhassan.common.result.RepositoryResult
import com.devhassan.common.result.ViewModelResult
import com.devhassan.data.repository.MoviesRepository
import com.devhassan.movies.model.MoviesScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesScreenViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var moviesScreenUiState by mutableStateOf(
        MoviesScreenUiState(
            ViewModelResult.INITIAL
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
                    Timber.d("Vm Remote Error Movies -> ${result.message}")
                    moviesScreenUiState = MoviesScreenUiState(
                        viewModelResult = ViewModelResult.ERROR,
                        message = result.message,
                        category = category
                    )
                }
                is RepositoryResult.Local -> {
                    Timber.d("Vm Local Success Movies -> ${result.data}")
                    moviesScreenUiState = MoviesScreenUiState(
                        ViewModelResult.SUCCESS,
                        result.data,
                        "Showing cached results!",
                        category = category
                    )
                }
                is RepositoryResult.Remote -> {
                    Timber.d("Vm Remote Success Movies -> ${result.data}")
                    moviesScreenUiState = MoviesScreenUiState(
                        ViewModelResult.SUCCESS,
                        result.data,
                        "Showing live results!",
                        category = category
                    )
                }
            }
        }
    }
}
