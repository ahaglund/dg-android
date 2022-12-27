package no.agens.dugnadsgjengen.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    background = white,
    surface = white,
    primary = greenAloe,
    primaryVariant = greenAloe,
    secondary = ui700,
    secondaryVariant = ui100,
    onPrimary = white,
    onBackground = coal,
    onSurface = coal,
    onSecondary = white,
    error = error,
)

@Composable
fun DugnadsgjengenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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