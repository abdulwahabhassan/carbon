package com.devhassan.movies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.devhassan.common.Extension.capitalizeEachWord
import com.devhassan.common.result.ViewModelResult
import com.devhassan.designsystem.R
import com.devhassan.designsystem.ui.theme.*
import com.devhassan.model.Category
import com.devhassan.movies.model.MoviesScreenUiState
import com.devhassan.movies.viewmodel.MoviesScreenViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun MoviesRoute(
    viewModel: MoviesScreenViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit,
    onLoadingStateActive: (Boolean) -> Unit
) {
    MoviesScreen(
        getMovies = viewModel::getMovies,
        moviesScreenUiState = viewModel.moviesScreenUiState,
        navigateToDetails = navigateToDetails,
        onLoadingStateActive = onLoadingStateActive
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesScreen(
    getMovies: (String) -> Unit,
    moviesScreenUiState: MoviesScreenUiState,
    onLoadingStateActive: (Boolean) -> Unit,
    navigateToDetails: (String) -> Unit,
) {

    val lazyGridState = rememberLazyGridState()
    val movies = moviesScreenUiState.movies?.collectAsLazyPagingItems()
    val showFilterOptions = rememberSaveable { mutableStateOf(false) }

    onLoadingStateActive(
        movies?.loadState?.append == LoadState.Loading ||
                movies?.loadState?.refresh == LoadState.Loading
    )

    when (moviesScreenUiState.viewModelResult) {
        ViewModelResult.SUCCESS -> {

            Column(modifier = Modifier.fillMaxSize()) {
                moviesScreenUiState.category?.capitalizeEachWord()?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = transparentPurple,
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = it,
                            color = purple,
                            style = MaterialTheme.typography.h5
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .clickable {
                                    showFilterOptions.value = true
                                }
                                .padding(6.dp),
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Filter",
                            tint = purple
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    state = lazyGridState,
                    contentPadding = PaddingValues(4.dp)
                ) {

                    items(movies?.itemCount ?: 0) { movieIndex ->
                        movies?.get(movieIndex)?.let { movie ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp, vertical = 4.dp),
                                onClick = {
                                    navigateToDetails(movies[movieIndex]?.id.toString())
                                },
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier.background(transparentPurple)
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = movie.posterPath),
                                        contentDescription = "Poster",
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .fillMaxWidth()
                                            .background(color = lightGrey)
                                            .height(200.dp),
                                        contentScale = ContentScale.Crop

                                    )
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp, vertical = 12.dp)
                                    ) {
                                        Text(
                                            text = movie.originalTitle,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.body1
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = movie.overview,
                                            color = grey,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.subtitle1
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                modifier = Modifier.weight(1f),
                                                text = LocalDate.parse(movie.releaseDate)
                                                    .format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
                                                    ?: "",
                                                color = grey,
                                                style = MaterialTheme.typography.subtitle1
                                            )
                                            Icon(
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .weight(0.3f)
                                                    .padding(end = 4.dp, start = 4.dp),
                                                imageVector = Icons.Rounded.Favorite,
                                                contentDescription = "Popularity",
                                                tint = pink
                                            )

                                            Text(
                                                text = String.format("%.1f", movie.voteAverage),
                                                color = grey,
                                                style = MaterialTheme.typography.subtitle1
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ViewModelResult.ERROR -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp),
                    painter = painterResource(id = R.drawable.ic_link_off),
                    contentDescription = "No transactions icon",
                    tint = Color.Unspecified
                )
                moviesScreenUiState.message?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 12.dp),
                        color = red
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(50),
                    onClick = { getMovies("popular") }) {
                    Text("Retry")
                }
            }
        }
        ViewModelResult.INITIAL -> {

        }
        ViewModelResult.LOADING -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(strokeWidth = 2.dp)
            }
        }
    }

    if (showFilterOptions.value) {
        Dialog(
            onDismissRequest = { showFilterOptions.value = false },
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 24.dp)

            ) {
                item {
                    Text(
                        text = "See what's trending..",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                            .padding(top = 2.dp, bottom = 8.dp, start = 12.dp, end = 12.dp),
                        color = black,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                    )
                }

                items(
                    listOf(
                        Category.NOW_PLAYING,
                        Category.POPULAR,
                        Category.TOP_RATED
                    )
                ) { category ->
                    Text(
                        text = category.name.capitalizeEachWord(),
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                            .background(
                                color = transparentPurple,
                                shape = RoundedCornerShape(50)
                            )
                            .clip(RoundedCornerShape(50))
                            .clickable {
                                getMovies(category.name.lowercase(Locale.getDefault()))
                                showFilterOptions.value = false
                            }
                            .padding(vertical = 6.dp, horizontal = 12.dp),
                        color = purple,
                        style = MaterialTheme.typography.h5
                    )
                }

            }

        }
    }

}