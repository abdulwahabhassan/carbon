package com.devhassan.movies.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devhassan.movies.ui.MoviesRoute
import com.devhassan.movies.ui.MoviesScreen
import com.devhassan.navigation.CarbonNavigationDestination

object MoviesNavigation: CarbonNavigationDestination {
    override val route = "movies_route"
    override val destination = "movies_destination"
}

fun NavGraphBuilder.moviesNavigationGraph() {
    composable(route = MoviesNavigation.route) {
        MoviesRoute()
    }
}