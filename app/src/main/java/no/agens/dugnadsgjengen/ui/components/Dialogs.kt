package no.agens.dugnadsgjengen.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import no.agens.dugnadsgjengen.ui.theme.greenAloe

@Composable
fun VoluntaryTaskSelectorDialog(
    dismissButtonTitle: String? = null,
    onDismissAction: () -> Unit,
    onConfirmAction: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissAction,
        text = content,
        dismissButton = {
            TextButton(
                onClick = onDismissAction,
            ) {
                Text(
                    text = dismissButtonTitle ?: "Avbryt",
                    style = MaterialTheme.typography.button.copy(color = greenAloe)
                )
            }
        },
        confirmButton = {

        }
    )
}