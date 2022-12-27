package no.agens.dugnadsgjengen.services.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import no.agens.dugnadsgjengen.models.*

object FirebaseService {

    fun addVoluntary(voluntary: Voluntary): Result<Unit> {
        val docRef = FirebaseFirestore.getInstance()
            .collection("voluntaries")
            .document()
            .set(voluntary.mapper())
            .addOnSuccessListener { documentReference ->
                Log.d("AH", "DocumentSnapshot added with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AH", "Error adding document", e)
            }

        return if (docRef.exception != null) {
            Result.failure(docRef.exception!!)
        } else {
            Result.success(Unit)
        }
    }

    fun deleteVoluntary(id: String): Result<Unit> {
        val task = FirebaseFirestore.getInstance()
            .collection("voluntaries")
            .document(id)
            .delete()

        return if (task.exception != null) {
            Result.failure(task.exception!!)
        } else {
            Result.success(Unit)
        }
    }

    suspend fun getVoluntaries(): List<Voluntary> {
        val voluntaryDtos = FirebaseFirestore.getInstance()
            .collection("voluntaries")
            .get()
            .await()
            .toObjects(VoluntaryDto::class.java)

        return voluntaryDtos.mapNotNull { it.mapper() }
    }

    suspend fun updateVoluntaryTask(
        voluntaryId: String,
        dgTaskName: String,
    ): Result<Unit> {
        return try {
            FirebaseFirestore.getInstance()
                .collection("voluntaries")
                .document(voluntaryId)
                .update("dgTask", dgTaskName)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTasks(): List<DgTask> {
        val taskDtos = FirebaseFirestore.getInstance()
            .collection("tasks")
            .get()
            .await()
            .toObjects(DgTaskDto::class.java)

        return taskDtos.mapNotNull { it.mapper() }
    }

    fun addTask(task: DgTask): Result<Unit> {
        val docRef = FirebaseFirestore.getInstance()
            .collection("tasks")
            .add(task)
            .addOnSuccessListener { documentReference ->
                Log.d("AH", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("AH", "Error adding document", e)
            }

        return if (docRef.exception != null) {
            Result.failure(docRef.exception!!)
        } else {
            Result.success(Unit)
        }
    }

    fun addTeam(team: Team): Result<Unit> {
        val docRef = FirebaseFirestore.getInstance()
            .collection("teams")
            .document()
            .set(team.dtoMapper())
            .addOnSuccessListener { documentReference ->
                Log.d("AH", "DocumentSnapshot added with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AH", "Error adding team", e)
            }

        return if (docRef.exception != null) {
            Result.failure(docRef.exception!!)
        } else {
            Result.success(Unit)
        }
    }
}