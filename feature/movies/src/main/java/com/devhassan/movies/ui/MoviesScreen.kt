package com.devhassan.movies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.devhassan.common.result.ViewModelResult
import com.devhassan.designsystem.R
import com.devhassan.designsystem.ui.theme.*
import com.devhassan.movies.model.MoviesScreenUiState
import com.devhassan.movies.viewmodel.MoviesScreenViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MoviesRoute(
    viewModel: MoviesScreenViewModel = hiltViewModel(),
//    navigationDestination: (CarbonNavigationDestination, String) -> Unit
    onLoadingStateActive: (Boolean) -> Unit
) {
    MoviesScreen(
        getMovies = viewModel::getMovies,
        moviesScreenUiState = viewModel.moviesScreenUiState,
//        navigationDestination = navigationDestination
        onLoadingStateActive = onLoadingStateActive
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesScreen(
    getMovies: (String) -> Unit,
    moviesScreenUiState: MoviesScreenUiState,
    onLoadingStateActive: (Boolean) -> Unit
//    navigationDestination: (CarbonNavigationDestination, String) -> Unit
) {

    val lazyGridState = rememberLazyGridState()
    val movies = moviesScreenUiState.movies?.collectAsLazyPagingItems()

    onLoadingStateActive(
        movies?.loadState?.append == LoadState.Loading ||
                movies?.loadState?.refresh == LoadState.Loading
    )

    when (moviesScreenUiState.viewModelResult) {
        ViewModelResult.SUCCESS -> {
            Box(modifier = Modifier.fillMaxSize()) {
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
                                    .padding(horizontal = 4.dp, vertical = 4.dp), onClick = {}, shape = RoundedCornerShape(10.dp)
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
                                                text = movie.voteAverage.toString(),
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
                            .background(
                                color = transparentPink,
                                shape = RoundedCornerShape(50)
                            )
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
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview() {
//    MoviesScreen(
//        getMovies = { category -> },
//        moviesScreenUiState = MoviesScreenUiState(
//            ViewModelResult.SUCCESS, listOf(
//                DomainMovie(
//                    true,
//                    "",
//                    1,
//                    "English",
//                    "Some Title",
//                    "Some random overview of the movie",
//                    4.00,
//                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_JRWUb65vA4_cUXaOwOFzlhB-npSVSf_G8eZAAY8Zf4fAx604Uhq3_lvqWyP0zufjjlM&usqp=CAU",
//                    "2022-10-11",
//                    "",
//                    true,
//                    1.00,
//                    100
//                ),
//                DomainMovie(
//                    false,
//                    "",
//                    2,
//                    "French",
//                    "Another Title to show",
//                    "Some random overview of the movie",
//                    5.00,
//                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_JRWUb65vA4_cUXaOwOFzlhB-npSVSf_G8eZAAY8Zf4fAx604Uhq3_lvqWyP0zufjjlM&usqp=CAU",
//                    "2022-09-01",
//                    "",
//                    true,
//                    1.00,
//                    50
//                ),
//                DomainMovie(
//                    false,
//                    "",
//                    3,
//                    "Aussie",
//                    "Glory days",
//                    "Some random overview of the movie",
//                    5.00,
//                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_JRWUb65vA4_cUXaOwOFzlhB-npSVSf_G8eZAAY8Zf4fAx604Uhq3_lvqWyP0zufjjlM&usqp=CAU",
//                    "2022-04-23",
//                    "",
//                    true,
//                    1.00,
//                    50
//                )
//            ),
//            message = "Check your internet connection"
//        )
//    )
}