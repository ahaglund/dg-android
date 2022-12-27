package no.agens.dugnadsgjengen.ui.screens.baller

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.agens.dugnadsgjengen.ReduxViewModel
import no.agens.dugnadsgjengen.models.Team
import no.agens.dugnadsgjengen.services.firebase.FirebaseTeamsService

data class BalforbrukViewModelState(
    val teams: List<Team> = emptyList(),
    val isLoading: Boolean = false,
)

class BallforbrukViewModel : ReduxViewModel<BalforbrukViewModelState>(BalforbrukViewModelState()) {

    fun getTeams() {
        viewModelScope.launchSetState {
            copy(isLoading = true)
        }
        viewModelScope.launch {
            val teams = FirebaseTeamsService.getTeams()
            setState {
                copy(
                    teams = teams,
                    isLoading = false,
                )
            }
        }
    }

    fun updateBallforbrukForTeams(
        team: Team,
        numberOfShuttles: Float,
    ) {
        if (team.id == null) {
            return
        }
        viewModelScope.launch {
            val temp = state.value.teams.filter { it.name != team.name }
            val newList = temp + team.copy(ballforbruk = numberOfShuttles)
            setState {
                copy(teams = newList)
            }

            val result = FirebaseTeamsService.updateBallforbrukForTeam(
                teamId = team.id,
                numberOfShuttles = numberOfShuttles,
            )
            if (result.isFailure) {
                // TODO: Handle error
            }
        }
    }
}