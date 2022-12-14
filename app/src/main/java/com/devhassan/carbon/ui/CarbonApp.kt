package com.devhassan.carbon.ui

import android.annotation.SuppressLint
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devhassan.designsystem.R
import com.devhassan.carbon.navigation.CarbonNavHost
import com.devhassan.designsystem.ui.theme.CarbonTheme
import com.devhassan.designsystem.ui.theme.purple
import com.devhassan.movies.navigation.MoviesDestination

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CarbonApp(
    appState: CarbonAppState = rememberCarbonAppState()
) {

    val isLoadingState = rememberSaveable() { mutableStateOf(true) }

    CarbonTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    elevation = 2.dp,
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (appState.currentDestination?.route != MoviesDestination.route) {
                            Icon(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable {
                                        appState.onBackClick()
                                    }
                                    .padding(6.dp),
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "Back arrow",
                                tint = purple
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                        }
                        Icon(
                            modifier = if (appState.currentDestination?.route != MoviesDestination.route)
                                Modifier.width(70.dp) else Modifier,
                            painter = painterResource(id = R.drawable.carbon_logo),
                            contentDescription = "Carbon logo",
                            tint = Color.Unspecified
                        )
                        androidx.compose.animation.AnimatedVisibility(
                            visible = isLoadingState.value,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            CircularProgressIndicator(
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        ) {
            CarbonNavHost(
                navController = appState.navController,
                onNavigateToDestination = appState::navigate,
                onLoadingStateActive = { bool ->
                    isLoadingState.value = bool
                }
            )

        }
    }

}

@Composable
@Preview(showBackground = true)
fun CarbonAppPreview() {
    CarbonApp()
}

