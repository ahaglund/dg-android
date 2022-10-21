package no.agens.dugnadsgjengen.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun DgFab(
    visible: Boolean,
    onClick: () -> Unit,
) {
    val scale by animateFloatAsState(targetValue = if (visible) 1f else 0f)
    if (scale > 0.1f) {
        FloatingActionButton(
            onClick = onClick,
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Legg til frivillig")
        }
    }
}