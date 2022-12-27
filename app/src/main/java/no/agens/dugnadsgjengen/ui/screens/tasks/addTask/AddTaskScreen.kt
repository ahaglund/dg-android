package no.agens.dugnadsgjengen.ui.screens.tasks.addTask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import no.agens.dugnadsgjengen.ui.components.ColorSelector
import no.agens.dugnadsgjengen.ui.components.HeightSpacer
import no.agens.dugnadsgjengen.ui.components.InputFieldWithLabel
import no.agens.dugnadsgjengen.ui.components.PrimaryButton

@Composable
fun AddTaskScreen(
    viewModel: AddTaskViewModel = viewModel(),
    navController: NavHostController,
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val uiState = viewModel.state.collectAsState().value

    Surface(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Column {
            InputFieldWithLabel(
                label = {
                    Text(
                        text = "Navn",
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                },
                value = uiState.task?.name ?: "",
                onValueChange = { viewModel.setTaskName(it) },
                scrollState = scrollState,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            HeightSpacer(height = 20.dp)
            InputFieldWithLabel(
                label = {
                    Text(
                        text = "Emoji",
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    )
                },
                value = uiState.task.emoji ?: "",
                onValueChange = { viewModel.setEmoji(it) },
                scrollState = scrollState,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )
            ColorSelector(
                selectedColorHash = uiState.task.colorHash,
                onColorSelected = { viewModel.setTaskColor(it) }
            )
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                text = "Legg til",
                isEnabled = uiState.task.name.isNotEmpty(),
                onClick = {
                    viewModel.addTask(
                        task = uiState.task,
                        onSuccess = { navController.popBackStack() }
                    )
                }
            )
        }
    }
}