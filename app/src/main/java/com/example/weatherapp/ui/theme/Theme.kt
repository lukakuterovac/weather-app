package com.example.weatherapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Primary,
    secondary = Color.Black,
    background = Gray900,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = Gray700,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = Primary,
    secondary = Color.White,
    background = Gray100,
    onPrimary = Color.White,
    onBackground = Color.Black,
    surface = Gray300,
    onSurface = Color.Black
)

@Composable
fun WeatherAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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
