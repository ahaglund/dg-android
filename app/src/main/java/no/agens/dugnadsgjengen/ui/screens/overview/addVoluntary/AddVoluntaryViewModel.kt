package no.agens.dugnadsgjengen.ui.screens.overview.addVoluntary

import androidx.lifecycle.viewModelScope
import no.agens.dugnadsgjengen.ReduxViewModel
import no.agens.dugnadsgjengen.models.Voluntary
import no.agens.dugnadsgjengen.services.firebase.FirebaseService

data class AddVoluntaryState(
    val voluntary: Voluntary? = null,
    val errorMessage: String? = null
)

class AddVoluntaryViewModel : ReduxViewModel<AddVoluntaryState>(AddVoluntaryState()) {

    fun addVoluntary(
        voluntary: Voluntary,
        onSuccess: () -> Unit
    ) {
        val result = FirebaseService.addVoluntary(voluntary)

        if (result.isFailure) {
            viewModelScope.launchSetState {
                copy(
                    errorMessage = "Kunne ikke legge til ny frivillig"
                )
            }
        } else {
            onSuccess()
        }

    }

    fun setVoluntaryName(name: String) {
        viewModelScope.launchSetState {
            if (state.value.voluntary != null ) {
                copy(
                    voluntary = state.value.voluntary?.copy(name = name)
                )
            } else {
                copy(
                    voluntary = Voluntary(name = name, phoneNumber = null)
                )
            }
        }
    }

    fun setVoluntaryPhoneNumber(phoneNumber: String) {
        viewModelScope.launchSetState {
            copy(
                voluntary = state.value.voluntary?.copy(phoneNumber = phoneNumber)
            )
        }
    }
}