package no.agens.dugnadsgjengen.ui.screens.overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import no.agens.dugnadsgjengen.models.DgTask
import no.agens.dugnadsgjengen.models.Voluntary
import no.agens.dugnadsgjengen.ui.components.DgTaskButton
import no.agens.dugnadsgjengen.ui.components.HeightSpacer
import no.agens.dugnadsgjengen.ui.components.Title2
import no.agens.dugnadsgjengen.ui.components.Title3

@Composable
fun ChangeVoluntaryTaskDialog(
    dgTasks: List<DgTask>,
    voluntary: Voluntary?,
    onDismiss: () -> Unit,
    onTaskSelected: (DgTask) -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Title2(
                    text = "Velg oppgave for",
                )
                HeightSpacer(height = 16.dp)
                Title3(text = voluntary?.name ?: "")
                HeightSpacer(height = 16.dp)

                dgTasks.filter { it.name != "ledig" }
                    .chunked(2)
                    .onEach {
                        Row(
                            modifier = Modifier.padding(top = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            DgTaskButton(
                                modifier = Modifier.weight(1f),
                                task = it.first(),
                                onClick = onTaskSelected,
                            )
                            if (it.size > 1) {
                                DgTaskButton(
                                    modifier = Modifier.weight(1f),
                                    task = it.last(),
                                    onClick = onTaskSelected,
                                )
                            }
                        }
                    }
            }
        }
    }
}