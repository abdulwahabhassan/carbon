package com.devhassan.details.navigation

import android.net.Uri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devhassan.details.ui.DetailsRoute
import com.devhassan.navigation.CarbonNavigationDestination

object DetailsDestination : CarbonNavigationDestination {
    const val movieIdArg = "movieId"
    override val route = "details_route/{$movieIdArg}"
    override val destination = "details_destination"

    fun createNavigationRoute(movieIdArg: String): String {
        val encodedId = Uri.encode(movieIdArg)
        return "details_route/$encodedId"
    }

    fun fromNavArgs(entry: NavBackStackEntry): String {
        val encodedId = entry.arguments?.getString(movieIdArg)!!
        return Uri.decode(encodedId)
    }
}

fun NavGraphBuilder.detailsNavigationGraph() {
    composable(
        route = DetailsDestination.route,
        arguments = listOf(
            navArgument(DetailsDestination.movieIdArg) { type = NavType.StringType }
        )) { navBackstackEntry ->
        DetailsRoute(movieId = navBackstackEntry.arguments?.getString(DetailsDestination.movieIdArg))
    }

}