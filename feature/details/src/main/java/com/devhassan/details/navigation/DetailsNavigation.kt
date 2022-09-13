package com.devhassan.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devhassan.details.ui.DetailsRoute
import com.devhassan.navigation.CarbonNavigationDestination

object DetailsNavigation: CarbonNavigationDestination {
    override val route = "movies_route"
    override val destination = "movies_destination"
}

fun NavGraphBuilder.detailsNavigationGraph() {
    composable(route = DetailsNavigation.route) {
        DetailsRoute()
    }
}