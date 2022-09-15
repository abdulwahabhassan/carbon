package com.devhassan.carbon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devhassan.carbon.ui.CarbonApp
import com.devhassan.designsystem.ui.theme.CarbonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarbonApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CarbonTheme {
        CarbonApp()
    }
}