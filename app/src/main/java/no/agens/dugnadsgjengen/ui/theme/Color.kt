package no.agens.dugnadsgjengen.ui.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val white = Color(0xFFFFFFFF)
val black = Color(0xFF000000)
val ui100 = Color(0xFFF4F4F4)
val ui200 = Color(0xFFEDEDED)
val ui300 = Color(0xFFCBCBCB)
val ui500 = Color(0xFF9B9B9B)
val ui700 = Color(0xFF5A5A5A)
val infoBg = Color(0xFFE5F1F8)
val info = Color(0xFF4A77A9)
val successBg = Color(0xFFDEF0E5)
val errorBg = Color(0xFFFEEFED)
val warningBg = Color(0xFFFAF0C9)
val avatarBorderColor = Color(0xFFCBCBCB)

val previewBackgroundColor = Color(color = 0xFF2A2422).copy(alpha = 0.5f)

val coal = Color(0xFF222222)
val transparent = Color(0x00000000)
val greenAloe = Color(0xFF3F6F61)
val error = Color(0xFFC3363E)
val success = Color(0xFF1D7C27)
val onError = Color(0xFFFEEFED)

val MainColorPalette = lightColors(
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
