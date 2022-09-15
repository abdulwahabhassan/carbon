package com.devhassan.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devhassan.details.ui.DetailsRoute
import com.devhassan.navigation.CarbonNavigationDestination

object DetailsDestination: CarbonNavigationDestination {
    override val route = "details_route"
    override val destination = "details_destination"
}

fun NavGraphBuilder.detailsNavigationGraph() {
    composable(route = DetailsDestination.route) {
        DetailsRoute()
    }
}