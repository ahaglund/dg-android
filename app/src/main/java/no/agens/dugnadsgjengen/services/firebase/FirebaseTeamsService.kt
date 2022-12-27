package no.agens.dugnadsgjengen.services.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import no.agens.dugnadsgjengen.models.Team
import no.agens.dugnadsgjengen.models.TeamDto

object FirebaseTeamsService {

    suspend fun getTeams(): List<Team> {
        val teamDtos = FirebaseFirestore.getInstance()
            .collection("teams")
            .get()
            .await()
            .toObjects(TeamDto::class.java)

        return teamDtos.mapNotNull { it.mapper() }
    }

    suspend fun updateBallforbrukForTeam(
        teamId: String,
        numberOfShuttles: Float,
    ): Result<Unit> {
        return try {
            FirebaseFirestore.getInstance()
                .collection("teams")
                .document(teamId)
                .update("ballforbruk", numberOfShuttles)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}