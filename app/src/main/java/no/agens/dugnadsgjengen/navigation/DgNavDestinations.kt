package no.agens.dugnadsgjengen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import no.agens.dugnadsgjengen.ui.screens.addVoluntary.AddVoluntaryScreen
import no.agens.dugnadsgjengen.ui.screens.overview.OverviewScreen
import no.agens.dugnadsgjengen.ui.screens.settings.SettingsScreen
import no.agens.dugnadsgjengen.ui.screens.tasks.TasksScreen

interface DgNavDestination {
    val icon: ImageVector
    val selectedIcon: ImageVector
    val route: String
    val name: String
    val screen: @Composable () -> Unit
}

object Overview : DgNavDestination {
    override val icon = Icons.Outlined.Home
    override val selectedIcon = Icons.Filled.Home
    override val route = "overview"
    override val name = "Oversikt"
    override val screen: @Composable () -> Unit = { OverviewScreen() }
}

object Settings : DgNavDestination {
    override val icon = Icons.Outlined.Settings
    override val selectedIcon = Icons.Filled.Settings
    override val route = "settings"
    override val name = "Innstillinger"
    override val screen: @Composable () -> Unit = { SettingsScreen() }
}

object Tasks : DgNavDestination {
    override val icon = Icons.Outlined.Check
    override val selectedIcon = Icons.Filled.Check
    override val route = "tasks"
    override val name = "Oppgaver"
    override val screen: @Composable () -> Unit = { TasksScreen() }
}

object AddVoluntary : DgNavDestination {
    override val icon = Icons.Outlined.Add
    override val selectedIcon = Icons.Filled.Add
    override val route = "add_voluntary"
    override val name = "Legg til frivillig"
    override val screen: @Composable () -> Unit = { AddVoluntaryScreen() }
}


val dgBottomBarDestinations = listOf(Overview, Tasks, Settings)