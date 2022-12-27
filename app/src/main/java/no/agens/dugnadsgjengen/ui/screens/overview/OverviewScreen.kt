package no.agens.dugnadsgjengen.ui.screens.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import no.agens.dugnadsgjengen.DgConstants
import no.agens.dugnadsgjengen.ui.components.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = viewModel()
) {

    val uiState by viewModel.state.collectAsState()
    val pagerState = rememberPagerState(initialPage = 0)
    val pages = listOf("Alle", "Ledig", "Opptatt") //+ uiState.dgTasks.map { it.name }
    val scope = rememberCoroutineScope()
    var showChangeVoluntaryTaskDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getVoluntaries()
        viewModel.getTasks()
        viewModel.shouldShowBottomComponent()
    }

    Column {
        ViewPagerTabs(
            pagerState = pagerState,
            pages = pages,
            onTabSelected = { index ->
                if (index < pagerState.pageCount) {
                    scope.launch {
                        pagerState.animateScrollToPage(page = index)
                    }
                }
            },
        )
        Divider()
        VoluntaryPager(
            pagerState = pagerState,
            numPages = pages.size,
            voluntaries = uiState.voluntaries,
            dgTasks = uiState.dgTasks,
            onVoluntaryClicked = {
                if (it.dgTask == DgConstants.DEFAULT_TASK_LEDIG.name) {
                    viewModel.setSelectedVoluntary(it)
                    showChangeVoluntaryTaskDialog = true
                } else {
                    viewModel.setVoluntaryTask(it, "ledig")
                }
            },
            isLoading = uiState.isLoading,
        )

        // TODO: Move into VoluntaryPager when Voluntary.availableTasks is updated
        if (showChangeVoluntaryTaskDialog) {
            ChangeVoluntaryTaskDialog(
                dgTasks = uiState.dgTasks,
                voluntary = uiState.selectedVoluntary,
                onDismiss = {
                    showChangeVoluntaryTaskDialog = false
                },
                onTaskSelected = { task ->
                    uiState.selectedVoluntary?.let {
                        viewModel.setVoluntaryTask(it, task.name)
                    }
                    showChangeVoluntaryTaskDialog = false
                    viewModel.setSelectedVoluntary(null)
                }
            )
        }
    }
}