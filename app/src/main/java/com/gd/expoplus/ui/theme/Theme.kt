package com.gd.expoplus.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryGreen,

    background = DarkBackground,

    surface = SurfaceDark,

    onPrimary = TextWhite,

    onBackground = TextWhite,

    onSurface = TextWhite
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryGreen,

    background = DarkBackground,

    surface = SurfaceDark,

    onPrimary = TextWhite,

    onBackground = TextWhite,

    onSurface = TextWhite

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ExpoPlusTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}