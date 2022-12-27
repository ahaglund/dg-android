package no.agens.dugnadsgjengen.ui.screens.tasks.addTask

import androidx.lifecycle.viewModelScope
import no.agens.dugnadsgjengen.ReduxViewModel
import no.agens.dugnadsgjengen.models.DgTask
import no.agens.dugnadsgjengen.services.firebase.FirebaseService

data class AddTaskViewModelState(
    val task: DgTask = DgTask(name = "", colorHash = -1, emoji = ""),
    val errorMessage: String? = null,
)

class AddTaskViewModel : ReduxViewModel<AddTaskViewModelState>(AddTaskViewModelState()) {

    fun addTask(
        task: DgTask,
        onSuccess: () -> Unit
    ) {
        val result = FirebaseService.addTask(task)

        if (result.isFailure) {
            viewModelScope.launchSetState {
                copy(
                    errorMessage = "Kunne ikke legge til ny oppgave"
                )
            }
        } else {
            onSuccess()
        }

    }

    fun setTaskName(name: String) {
        viewModelScope.launchSetState {
            copy(
                task = state.value.task?.copy(name = name)
            )
        }
    }

    fun setTaskColor(colorHash: Int) {
        viewModelScope.launchSetState {
            copy(
                task = task?.copy(colorHash = colorHash)
            )
        }
    }

    fun setEmoji(emoji: String) {
        viewModelScope.launchSetState {
            copy(
                task = task?.copy(emoji = emoji)
            )
        }
    }
}