package no.agens.dugnadsgjengen.ui.screens.baller.addTeam

import androidx.lifecycle.viewModelScope
import no.agens.dugnadsgjengen.ReduxViewModel
import no.agens.dugnadsgjengen.models.Team
import no.agens.dugnadsgjengen.services.firebase.FirebaseService

data class AddTeamViewModelState(
    val team: Team? = Team(name = "", ballforbruk = 0f),
    val errorMessage: String? = null
)


class AddTeamViewModel : ReduxViewModel<AddTeamViewModelState>(AddTeamViewModelState()) {

    fun addTeam(
        team: Team,
        onSuccess: () -> Unit
    ) {
        val result = FirebaseService.addTeam(team)

        if (result.isFailure) {
            viewModelScope.launchSetState {
                copy(
                    errorMessage = "Kunne ikke legge til nytt lag."
                )
            }
        } else {
            onSuccess()
        }
    }

    fun setVoluntaryName(name: String) {
        viewModelScope.launchSetState {
            copy(team = team?.copy(name = name))
        }
    }
}
