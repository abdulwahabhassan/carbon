package com.devhassan.movies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.devhassan.common.result.ViewModelResult
import com.devhassan.model.DomainMovie
import com.devhassan.movies.model.MoviesScreenUiState
import com.devhassan.movies.viewmodel.MoviesScreenViewModel

@Composable

fun MoviesRoute(viewModel: MoviesScreenViewModel = hiltViewModel()) {
    MoviesScreen(
        getMovies = viewModel::getMovies,
        moviesScreenUiState = viewModel.moviesScreenUiState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesScreen(
    getMovies: (String) -> Unit,
    moviesScreenUiState: MoviesScreenUiState
) {
    LazyRow() {
        items(listOf<DomainMovie>()) {
            Card(onClick = {}) {
                Column {
                    Text(text = "Hello")
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun MovieScreenPreview() {
    MoviesScreen(
        getMovies = { category -> },
        moviesScreenUiState = MoviesScreenUiState(
            ViewModelResult.SUCCESS, listOf(
                DomainMovie(
                    true,
                    "",
                    1,
                    "English",
                    "Some Title",
                    "Some random overview of the movie",
                    4.00,
                    "",
                    "2022-10-11 12:00:00",
                    "",
                    true,
                    1.00,
                    100
                )
            )
        )
    )
}