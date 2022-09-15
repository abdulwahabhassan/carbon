package com.devhassan.details.ui

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
import com.devhassan.details.model.DetailsScreenUiState
import com.devhassan.details.viewmodel.DetailsScreenViewModel
import com.devhassan.model.*


@Composable
fun DetailsRoute(viewModel: DetailsScreenViewModel = hiltViewModel()) {
    DetailsScreen(
        getMovieDetails = viewModel::getMovieDetails,
        detailsScreenUiState = viewModel.detailsScreenUiState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    getMovieDetails: (Long) -> Unit,
    detailsScreenUiState: DetailsScreenUiState
) {

    Text(text = "Hello")
}

@Composable
@Preview(showBackground = true)
fun MovieScreenPreview() {
    DetailsScreen(
        { _ -> },
        detailsScreenUiState = DetailsScreenUiState(
            ViewModelResult.SUCCESS,
            DomainDetails(
                true,
                "",
                170000,
                listOf(DomainGenre(1, "Sci-Fi")),
                "",
                1,
                "",
                "",
                "",
                "",
                3.5,
                "",
                listOf(DomainProductionCompany(1, "", "Marvel Studios", "USA")),
                "2019-10-11 00:00:00",
                230000000,
                120,
                listOf(DomainSpokenLanguage("En", "", "English")),
                "",
                "",
                "Wolf Kingdom",
                true,
                5.00,
                200,
            )
        )
    )
}