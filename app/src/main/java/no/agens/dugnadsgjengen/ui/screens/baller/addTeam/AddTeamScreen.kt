package no.agens.dugnadsgjengen.ui.screens.baller.addTeam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import no.agens.dugnadsgjengen.ui.components.HeightSpacer
import no.agens.dugnadsgjengen.ui.components.InputFieldWithLabel
import no.agens.dugnadsgjengen.ui.components.PrimaryButton

@Composable
fun AddTeamScreen(
    viewModel: AddTeamViewModel = viewModel(),
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
                value = uiState.team?.name ?: "",
                onValueChange = { viewModel.setVoluntaryName(it) },
                scrollState = scrollState,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            HeightSpacer(height = 20.dp)

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                text = "Legg til",
                isEnabled = !uiState.team?.name.isNullOrBlank(),
                onClick = {
                    if (uiState.team != null) {
                        viewModel.addTeam(
                            team = uiState.team,
                            onSuccess = { navController.popBackStack() }
                        )
                    }
                }
            )
        }
    }
}