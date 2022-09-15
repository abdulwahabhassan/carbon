package com.devhassan.carbon.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devhassan.navigation.CarbonNavigationDestination

@Composable
fun rememberCarbonAppState(
    navController: NavHostController = rememberNavController()
): CarbonAppState {
    return remember(navController) { CarbonAppState(navController) }
}

@Stable
class CarbonAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun navigate(destination: CarbonNavigationDestination, route: String? = null) {
        navController.navigate(route ?: destination.route) {
            launchSingleTop = true
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}