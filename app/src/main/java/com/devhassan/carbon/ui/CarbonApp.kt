package com.devhassan.carbon.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devhassan.carbon.R
import com.devhassan.carbon.theme.CarbonTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CarbonApp() {

    CarbonTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    elevation = 2.dp,
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            painter = painterResource(id = R.drawable.carbon_logo),
                            contentDescription = "Carbon logo",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        ) {

        }
    }

}

@Composable
@Preview(showBackground = true)
fun CarbonAppPreview() {
    CarbonApp()

}

