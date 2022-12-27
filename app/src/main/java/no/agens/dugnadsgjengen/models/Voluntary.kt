package no.agens.dugnadsgjengen.models

import com.google.firebase.firestore.DocumentId
import no.agens.dugnadsgjengen.DgConstants

data class Voluntary(
    val id: String? = null,
    val name: String,
    val dgTask: String = DgConstants.DEFAULT_TASK_LEDIG.name,
    val phoneNumber: String? = null,
    val availableStates: List<String> = listOf("ledig", "opptatt"),
) {
    fun mapper(): VoluntaryDto {
        return VoluntaryDto(
            name = name,
            dgTask = dgTask,
            phoneNumber = phoneNumber,
            availableStates = availableStates,
        )
    }
}

data class VoluntaryDto(
    @DocumentId val id: String? = null,
    val name: String? = null,
    val dgTask: String = DgConstants.DEFAULT_TASK_LEDIG.name,
    val phoneNumber: String? = null,
    val availableStates: List<String> = listOf("ledig", "opptatt"),
) {
    fun mapper(): Voluntary? {
        return if (name == null || id == null) {
            null
        } else {
            Voluntary(
                id = id,
                name = name,
                dgTask = dgTask,
                phoneNumber = phoneNumber,
                availableStates = availableStates,
            )
        }
    }
}


