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
import androidx.compose.ui.graphics.vector.ImageVector

interface DgNavDestination {
    val icon: ImageVector
    val selectedIcon: ImageVector
    val route: String
    val name: String
}

object Overview : DgNavDestination {
    override val icon = Icons.Outlined.Home
    override val selectedIcon = Icons.Filled.Home
    override val route = "overview"
    override val name = "Oversikt"
}

object Baller : DgNavDestination {
    override val icon = Icons.Outlined.Settings
    override val selectedIcon = Icons.Filled.Settings
    override val route = "baller"
    override val name = "Ballforbruk"
}

object Tasks : DgNavDestination {
    override val icon = Icons.Outlined.Check
    override val selectedIcon = Icons.Filled.Check
    override val route = "tasks"
    override val name = "Oppgaver"
}

object AddVoluntary : DgNavDestination {
    override val icon = Icons.Outlined.Add
    override val selectedIcon = Icons.Filled.Add
    override val route = "add_voluntary"
    override val name = "Legg til frivillig"
}

object AddTask : DgNavDestination {
    override val icon = Icons.Outlined.Add
    override val selectedIcon = Icons.Filled.Add
    override val route = "add_task"
    override val name = "Legg til oppgave"
}

object AddTeam : DgNavDestination {
    override val icon = Icons.Outlined.Add
    override val selectedIcon = Icons.Filled.Add
    override val route = "add_team"
    override val name = "Legg til lag"
}

val dgBottomBarDestinations = listOf(Overview, Tasks, Baller)
val dgAllDestinations = listOf(Overview, Tasks, Baller, AddVoluntary, AddTask)