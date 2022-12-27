package no.agens.dugnadsgjengen.ui.screens.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import no.agens.dugnadsgjengen.ui.components.Loader
import no.agens.dugnadsgjengen.ui.components.TasksListView

@Composable
fun TasksScreen(
    viewModel: TasksViewModel = viewModel()
) {
    val uiState = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getTasks()
    }

    if (uiState.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Loader(
                modifier = Modifier.padding(top = 40.dp)
            )
        }

    } else {
        Column {
            TasksListView(
                tasks = uiState.tasks,
                onTaskClicked = {}
            )
        }
    }
}