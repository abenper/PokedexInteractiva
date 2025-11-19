package com.example.composepokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColors = lightColors(
    primary = md_theme_primary,
    onPrimary = md_theme_onPrimary,
    background = md_theme_background,
    onBackground = md_theme_onBackground
)

private val DarkColors = darkColors(
    primary = md_theme_primary,
    onPrimary = md_theme_onPrimary,
    background = md_theme_background,
    onBackground = md_theme_onBackground
)

@Composable
fun ComposePokedexTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (!darkTheme) LightColors else DarkColors

    MaterialTheme(
        colors = colors,
        typography = androidx.compose.material.Typography(),
        shapes = androidx.compose.material.Shapes(),
        content = content
    )
}