package com.devhassan.carbon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.devhassan.details.navigation.DetailsDestination
import com.devhassan.details.navigation.detailsNavigationGraph
import com.devhassan.movies.navigation.MoviesDestination
import com.devhassan.movies.navigation.moviesNavigationGraph
import com.devhassan.navigation.CarbonNavigationDestination

@Composable
fun CarbonNavHost(
    navController: NavHostController,
    onNavigateToDestination: (CarbonNavigationDestination, String) -> Unit,
    startDestination: String = MoviesDestination.route,
    onLoadingStateActive: (Boolean) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        moviesNavigationGraph(
            onLoadingStateActive = onLoadingStateActive,
            navigateToDetails = { movieId ->
                onNavigateToDestination(
                    DetailsDestination,
                    DetailsDestination.createNavigationRoute(movieId)
                )
            }
        )
        detailsNavigationGraph()
    }

}