package no.agens.dugnadsgjengen.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import no.agens.dugnadsgjengen.R

private val InterFamily = FontFamily(
    Font(R.font.inter_regular),
    Font(R.font.inter_semibold, weight = FontWeight.Bold),
)

val smallButtonStyle = TextStyle(
    fontFamily = InterFamily,
    fontWeight = FontWeight.W600,
    fontSize = 13.sp,
    lineHeight = 20.sp
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 18.sp,
        color = coal
    ),
    h1 = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.W600,
        fontStyle = FontStyle.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.4).sp,
        color = coal
    ),
    h2 = TextStyle(
        fontFamily = InterFamily,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W600,
        fontSize = 26.sp,
        lineHeight = 31.sp,
        letterSpacing = (-0.2).sp,
        color = coal
    ),
    h3 = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.W600,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        color = coal
    ),
    h4 = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.W700,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        color = coal,

        ),
    h5 = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        color = coal
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val bodySmall = TextStyle(
    fontFamily = InterFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    lineHeight = 20.sp
)