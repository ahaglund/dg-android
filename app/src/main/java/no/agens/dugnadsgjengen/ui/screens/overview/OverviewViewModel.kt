package no.agens.dugnadsgjengen.ui.screens.overview

import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import no.agens.dugnadsgjengen.R
import no.agens.dugnadsgjengen.ReduxViewModel
import no.agens.dugnadsgjengen.models.DgTask
import no.agens.dugnadsgjengen.models.Voluntary
import no.agens.dugnadsgjengen.services.firebase.FirebaseService
import timber.log.Timber

data class OverviewState(
    val voluntaries: List<Voluntary> = emptyList(),
    val isLoading: Boolean = false,
    val dgTasks: List<DgTask> = emptyList(),
    val selectedVoluntary: Voluntary? = null,
    val showBottomComponent: Boolean = false,
)

class OverviewViewModel : ReduxViewModel<OverviewState>(OverviewState()) {

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    init {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10 // 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                remoteConfig.fetchAndActivate().await()
            } catch (ex: Exception) {
                Timber.e(ex.message ?: "Exception")
            }
        }
    }

    fun getVoluntaries() {
        viewModelScope.launchSetState {
            copy(isLoading = true)
        }
        viewModelScope.launch {
            val voluntaries = FirebaseService.getVoluntaries()
            setState {
                copy(
                    voluntaries = voluntaries,
                    isLoading = false,
                )
            }
        }
    }

    fun shouldShowBottomComponent() {
        viewModelScope.launchSetState {
            val showBottomComponent = remoteConfig.getBoolean("show_bottom_component")
            Timber.d("AH: showBottomComponent = $showBottomComponent")
            copy(showBottomComponent = showBottomComponent)
        }
    }

    fun setVoluntaryTask(
        voluntary: Voluntary,
        dgTaskName: String,
    ) {
        if (voluntary.id == null) {
            return
        }
        viewModelScope.launch {
            val result = FirebaseService.updateVoluntaryTask(
                voluntary.id,
                dgTaskName = dgTaskName,
            )

            if (result.isSuccess) {
                val temp = state.value.voluntaries.filter { it.name != voluntary.name }
                val newList = temp + voluntary.copy(dgTask = dgTaskName)
                setState {
                    copy(voluntaries = newList)
                }
            }
        }
    }

    fun setSelectedVoluntary(
        voluntary: Voluntary?
    ) {
        viewModelScope.launchSetState {
            copy(
                selectedVoluntary = voluntary,
            )
        }
    }

    fun getTasks() {
        viewModelScope.launchSetState {
            copy(isLoading = true)
        }
        viewModelScope.launch {
            val tasks = FirebaseService.getTasks()
            setState {
                copy(
                    dgTasks = tasks,
                    isLoading = false,
                )
            }
        }
    }

//    fun getDummyVoluntaries() {
//        viewModelScope.launchSetState {
//            copy(
//                voluntaries = listOf(
//                    Voluntary(
//                        name = "Andreas Haglund",
//                        phoneNumber = "91808492",
//                        dgTask = "opptatt",
//                        availableStates = listOf("ledig", "opptatt", "teller", "kiosk")
//                    ),
//                    Voluntary(
//                        name = "Rolf Bjaanes",
//                        phoneNumber = "91808492",
//                        dgTask = "ledig",
//                        availableStates = listOf("ledig", "opptatt", "teller", "kiosk")
//                    ),
//                    Voluntary(
//                        name = "Jayson Mackie",
//                        phoneNumber = "91808492",
//                        dgTask = "kiosk",
//                        availableStates = listOf("ledig", "opptatt", "teller", "kiosk")
//                    ),
//                    Voluntary(
//                        name = "Knut Clausen",
//                        phoneNumber = "91808492",
//                        dgTask = "ledig",
//                        availableStates = listOf("ledig", "opptatt", "teller", "kiosk")
//                    ),
//                    Voluntary(
//                        name = "Jaran Flaath",
//                        phoneNumber = "91808492",
//                        dgTask = "teller",
//                        availableStates = listOf("ledig", "opptatt", "teller", "kiosk")
//                    ),
//                    Voluntary(
//                        name = "Trond Abusdal",
//                        phoneNumber = "91808492",
//                        dgTask = "ledig",
//                        availableStates = listOf("ledig", "opptatt", "teller", "kiosk")
//                    ),
//                    Voluntary(
//                        name = "Ole Kristian Sunnarvik",
//                        phoneNumber = "91808492",
//                        dgTask = "sekretariat",
//                        availableStates = listOf("ledig", "opptatt", "teller", "kiosk")
//                    ),
//                    Voluntary(
//                        name = "Marie Ringdal",
//                        phoneNumber = "91808492",
//                        dgTask = "ledig",
//                        availableStates = listOf("ledig", "opptatt", "teller", "kiosk")
//                    ),
//                    Voluntary(
//                        name = "Mikael Reiersølmoen",
//                        phoneNumber = "91808492",
//                        dgTask = "teller",
//                        availableStates = listOf("ledig", "opptatt", "teller", "kiosk")
//                    ),
//                )
//            )
//        }
//    }
}