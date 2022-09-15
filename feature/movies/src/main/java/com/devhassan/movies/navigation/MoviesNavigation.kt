package com.devhassan.movies.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devhassan.movies.ui.MoviesRoute
import com.devhassan.navigation.CarbonNavigationDestination

object MoviesDestination: CarbonNavigationDestination {
    override val route = "movies_route"
    override val destination = "movies_destination"
}

fun NavGraphBuilder.moviesNavigationGraph(
//    navigateToDestination: (CarbonNavigationDestination, String) -> Unit
) {
    composable(route = MoviesDestination.route) {
        MoviesRoute(
//            navigationDestination = navigateToDestination
        )
    }
}