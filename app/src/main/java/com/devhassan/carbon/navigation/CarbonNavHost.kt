package com.devhassan.carbon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.devhassan.navigation.CarbonNavigationDestination

@Composable
fun CarbonNavHost(
    navController: NavHostController,
    onNavigateToDestination: (CarbonNavigationDestination, String) -> Unit,
    startDestination: String = ForYouDestination.route
) {

}