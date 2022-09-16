package com.devhassan.details.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.devhassan.common.Extension.capitalizeEachWord
import com.devhassan.common.Extension.capitalizeFirstLetter
import com.devhassan.common.Util
import com.devhassan.common.result.ViewModelResult
import com.devhassan.designsystem.R
import com.devhassan.designsystem.ui.theme.*
import com.devhassan.details.model.DetailsScreenUiState
import com.devhassan.details.viewmodel.DetailsScreenViewModel
import com.devhassan.model.DomainDetails
import com.devhassan.model.DomainGenre
import com.devhassan.model.DomainProductionCompany
import com.devhassan.model.DomainSpokenLanguage
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun DetailsRoute(viewModel: DetailsScreenViewModel = hiltViewModel(), movieId: String?) {
    DetailsScreen(
        getMovieDetails = viewModel::getMovieDetails,
        detailsScreenUiState = viewModel.detailsScreenUiState,
        movieId = movieId
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(
    getMovieDetails: (Long) -> Unit,
    detailsScreenUiState: DetailsScreenUiState,
    movieId: String?
) {
    val scaffoldState = rememberScaffoldState()
    val isLoadingState =
        rememberSaveable(detailsScreenUiState.viewModelResult) {
            mutableStateOf(
                when (detailsScreenUiState.viewModelResult) {
                    ViewModelResult.LOADING, ViewModelResult.INITIAL -> true
                    ViewModelResult.SUCCESS, ViewModelResult.ERROR -> false
                }
            )
        }


    movieId?.toLong()?.let {
        LaunchedEffect(key1 = Unit, block = {
            getMovieDetails(it)
        })
        if (isLoadingState.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
        } else {
            if (detailsScreenUiState.viewModelResult == ViewModelResult.SUCCESS) {
                Scaffold(
                    scaffoldState = scaffoldState,
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            Image(
                                painter = rememberAsyncImagePainter(model = detailsScreenUiState.movieDetails?.backdropPath),
                                contentDescription = "Poster",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = lightGrey)
                                    .height(250.dp),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(
                                        text = detailsScreenUiState.movieDetails?.originalTitle
                                            ?: "",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.body1
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .padding(end = 4.dp, start = 4.dp),
                                            imageVector = Icons.Rounded.Favorite,
                                            contentDescription = "Popularity",
                                            tint = pink
                                        )
                                        Text(
                                            modifier = Modifier,
                                            text = String.format(
                                                "%.1f",
                                                detailsScreenUiState.movieDetails?.voteAverage
                                            ),
                                            color = black,
                                            fontWeight = FontWeight.Bold,
                                            style = MaterialTheme.typography.body1
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Start,
                                    ) {

                                        Row(
                                            modifier = Modifier,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_date),
                                                "Date",
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .padding(end = 4.dp),
                                                tint = purple
                                            )

                                            Text(
                                                modifier = Modifier,
                                                text = LocalDate
                                                    .parse(detailsScreenUiState.movieDetails?.releaseDate)
                                                    .format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
                                                    ?: "",
                                                color = grey,
                                                style = MaterialTheme.typography.body1
                                            )
                                        }


                                        Text(
                                            modifier = Modifier.padding(horizontal = 8.dp),
                                            text = "|",
                                            color = grey,
                                            style = MaterialTheme.typography.body1
                                        )

                                        Text(
                                            modifier = Modifier,
                                            text = detailsScreenUiState.movieDetails?.originalLanguage?.capitalizeFirstLetter()
                                                ?: "",
                                            color = grey,
                                            style = MaterialTheme.typography.body1
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = when (val votes =
                                            detailsScreenUiState.movieDetails?.voteCount ?: 0) {
                                            in 1000..999_999 -> votes.toString().dropLast(3) + "k"
                                            in 1_000_000..999_999_999 -> votes.toString()
                                                .dropLast(3) + "m"
                                            in 1_000_000_000..999_999_999_999 -> votes.toString()
                                                .dropLast(3) + "b"
                                            else -> votes.toString()
                                        } + " votes",
                                        color = green,
                                        style = MaterialTheme.typography.body1,
                                        modifier = Modifier
                                            .background(
                                                transparentGreen,
                                                RoundedCornerShape(4.dp)
                                            )
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    )

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    item {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_genre),
                                            contentDescription = "Genres",
                                            modifier = Modifier
                                                .size(24.dp)
                                                .padding(end = 4.dp),
                                            tint = purple
                                        )
                                    }
                                    items(detailsScreenUiState.movieDetails?.domainGenres?.map { it.name }
                                        ?: emptyList()) { item ->

                                        val itemBgColor = listOf(
                                            transparentBlue,
                                            transparentGreen,
                                            transparentPink,
                                            transparentYellow,
                                            transparentAsh,
                                            transparentPurple
                                        ).random()
                                        Text(
                                            modifier = Modifier
                                                .padding(end = 8.dp)
                                                .background(
                                                    color = itemBgColor,
                                                    shape = RoundedCornerShape(4.dp)
                                                )
                                                .padding(horizontal = 12.dp, vertical = 4.dp),
                                            text = item.capitalizeEachWord(),
                                            color = when (itemBgColor) {
                                                transparentBlue -> darkBlue
                                                transparentGreen -> green
                                                transparentPink -> red
                                                transparentYellow -> yellow
                                                transparentAsh -> black
                                                transparentPurple -> purple
                                                else -> grey
                                            },
                                            style = MaterialTheme.typography.body1
                                        )

                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_runtime),
                                        "Runtime",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(end = 4.dp),
                                        tint = purple
                                    )
                                    Text(
                                        text = (detailsScreenUiState.movieDetails?.runtime.toString()
                                            .plus(" minutes")),
                                        color = grey,
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_description),
                                        "Overview",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(end = 4.dp),
                                        tint = purple
                                    )
                                    Text(
                                        text = detailsScreenUiState.movieDetails?.overview ?: "",
                                        color = grey,
                                        style = MaterialTheme.typography.body1
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_budget),
                                        "Budget",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(end = 4.dp),
                                        tint = purple
                                    )

                                    Text(
                                        modifier = Modifier.padding(end = 16.dp),
                                        text = "Budget: $" + detailsScreenUiState.movieDetails?.budget?.let { budget ->
                                            Util.formatAmount(budget)
                                        },
                                        color = grey,
                                        style = MaterialTheme.typography.body1
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_revenue),
                                        "Revenue",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(end = 4.dp),
                                        tint = purple
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = "Revenue: $" + detailsScreenUiState.movieDetails?.revenue?.let { revenue ->
                                            Util.formatAmount(revenue)
                                        },
                                        color = grey,
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                    }

                }
            } else {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = detailsScreenUiState.message ?: "Unknown error",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 12.dp),
                        color = red
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(50),
                        onClick = { getMovieDetails(movieId.toLong()) }) {
                        Text("Retry")
                    }
                }

            }

        }

    } ?: Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MovieId args is missing!",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(
                    color = transparentPink,
                    shape = RoundedCornerShape(50)
                )
                .padding(vertical = 8.dp, horizontal = 12.dp),
            color = red
        )
    }

}

@Composable
@Preview(showBackground = true)
fun MovieScreenPreview() {
    CarbonTheme {
        DetailsScreen(
            { _ -> },
            detailsScreenUiState = DetailsScreenUiState(
                ViewModelResult.SUCCESS,
                DomainDetails(
                    true,
                    "",
                    170000,
                    listOf(DomainGenre(1, "Sci-Fi"), DomainGenre(2, "K drama")),
                    "",
                    1,
                    "",
                    "Fr",
                    "A Title About The Movie",
                    "Some overview about the movie",
                    3.5,
                    "https://image.tmdb.org/t/p/original/kMKyx1k8hWWscYFnPbnxxN4Eqo4.jpg",
                    listOf(DomainProductionCompany(1, "", "Marvel Studios", "USA")),
                    "2019-10-11",
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
            ),
            movieId = "1"
        )
    }

}