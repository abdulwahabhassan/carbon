package com.devhassan.designsystem.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = grey,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = black,
    onBackground = black,
    onSurface = black,
)

private val LightColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = grey,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = black,
    onBackground = black,
    onSurface = black,
)

@Composable
fun CarbonTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}