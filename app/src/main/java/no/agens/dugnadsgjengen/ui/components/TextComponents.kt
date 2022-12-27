package no.agens.dugnadsgjengen.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import no.agens.dugnadsgjengen.ui.theme.bodySmall

@Composable
fun Title1(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun Title2(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun Title3(
    text: String,
    modifier: Modifier = Modifier, color: Color = MaterialTheme.colors.onBackground,
    style: TextStyle = MaterialTheme.typography.h3,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        maxLines = maxLines,
    )
}

@Composable
fun BodySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onBackground,
    textAlign: TextAlign? = null,
    style: TextStyle = bodySmall
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun Body1(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    style: TextStyle = MaterialTheme.typography.body1,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = MaterialTheme.colors.onBackground
) {
    Text(
        text = text,
        textAlign = textAlign,
        modifier = modifier,
        fontWeight = fontWeight,
        color = color,
        style = style
    )
}