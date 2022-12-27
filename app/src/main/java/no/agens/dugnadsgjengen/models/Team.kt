package no.agens.dugnadsgjengen.models

import com.google.firebase.firestore.DocumentId

data class Team(
    val id: String? = null,
    val name: String,
    val ballforbruk: Float,
) {
    fun dtoMapper(): TeamDto {
        return TeamDto(
            name = name,
            ballforbruk = ballforbruk,
        )
    }
}

data class TeamDto(
    @DocumentId val id: String? = null,
    val name: String? = null,
    val ballforbruk: Float = 0f,
) {
    fun mapper(): Team? {
        return if (name == null || id == null) {
            null
        } else {
            return Team(
                id = id,
                name = name,
                ballforbruk = ballforbruk,
            )
        }
    }
}
