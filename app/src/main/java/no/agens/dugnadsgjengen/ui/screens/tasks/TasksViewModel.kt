package no.agens.dugnadsgjengen.ui.screens.tasks

import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch
import no.agens.dugnadsgjengen.ReduxViewModel
import no.agens.dugnadsgjengen.models.DgTask
import no.agens.dugnadsgjengen.models.Voluntary
import no.agens.dugnadsgjengen.services.firebase.FirebaseService

data class TasksViewModelState(
    val tasks: List<DgTask> = emptyList(),
    val isLoading: Boolean = false,
)

class TasksViewModel : ReduxViewModel<TasksViewModelState>(TasksViewModelState()) {

    fun getTasks() {
        viewModelScope.launchSetState {
            copy(isLoading = true)
        }
        viewModelScope.launch {
            val tasks = FirebaseService.getTasks()
            setState {
                copy(
                    tasks = tasks,
                    isLoading = false,
                )
            }
        }
    }

    fun getDummyTasks() {
        viewModelScope.launchSetState {
            copy(
                tasks = listOf()
            )
        }
    }
}