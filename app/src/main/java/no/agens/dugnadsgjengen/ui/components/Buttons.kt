package no.agens.dugnadsgjengen.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import no.agens.dugnadsgjengen.ui.theme.smallButtonStyle

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showLoadingState: Boolean = false,
    isEnabled: Boolean = true,
    isPlaceholder: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        disabledBackgroundColor = MaterialTheme.colors.primary.copy(
            alpha = 0.5f
        )
    ),
    textColor: Color = if (isEnabled) MaterialTheme.typography.button.color else Color.White,
    @DrawableRes icon: Int? = null,
) {
    BaseButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        showLoadingState = showLoadingState,
        isEnabled = isEnabled,
        colors = colors,
        textColor = textColor,
        icon = icon,
        isPlaceholder = isPlaceholder,
        elevation = null
    )
}

@Composable
private fun BaseButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showLoadingState: Boolean = false,
    isEnabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    textColor: Color = MaterialTheme.typography.button.color,
    border: BorderStroke? = null,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    @DrawableRes icon: Int? = null,
    shape: Shape = MaterialTheme.shapes.small,
    isSmall: Boolean = false,
    isPlaceholder: Boolean = false
) {
    Button(
        onClick = {
            if (isEnabled) {
                onClick()
            }
        },
        colors = colors,
        enabled = isEnabled,
        border = border,
        elevation = elevation,
        shape = shape,
        modifier = modifier.height(if (isSmall) 38.dp else 50.dp),
        contentPadding = PaddingValues(horizontal = if (isSmall) 12.dp else 24.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(
                visible = showLoadingState,
                modifier = Modifier.size(24.dp),
            ) {
                CircularProgressIndicator(
                    strokeWidth = 3.dp,
                    color = contentColorFor(MaterialTheme.colors.primary)
                )
            }

            if (icon != null && !showLoadingState) {
                Icon(
                    painterResource(id = icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            AnimatedVisibility(
                visible = !showLoadingState,
            ) {
                Text(
                    modifier = Modifier
                        .placeholder(
                            color = Color.White.copy(alpha = 0.2F),
                            shape = RoundedCornerShape(2.dp),
                            visible = isPlaceholder,
                            highlight = PlaceholderHighlight.shimmer(
                                progressForMaxAlpha = 0.3F,
                            ),
                        ),
                    text = text,
                    textAlign = TextAlign.Center,
                    style = if (isSmall) smallButtonStyle else MaterialTheme.typography.button,
                    color = textColor,
                    overflow = TextOverflow.Visible,
                    softWrap = false
                )
            }
        }
    }
}