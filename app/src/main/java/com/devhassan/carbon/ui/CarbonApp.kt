package com.devhassan.carbon.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devhassan.carbon.ui.theme.CarbonTheme

@Composable
fun CarbonApp() {

    CarbonTheme {
        Scaffold(
            topBar = {
                TopAppBar {

                }
            }
        ) {

        }
    }

}

@Composable
@Preview
fun CarbonAppPreview() {

    CarbonTheme {
        Scaffold(
            topBar = {
                TopAppBar {

                }
            }
        ) {

        }
    }

}

