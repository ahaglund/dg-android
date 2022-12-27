package no.agens.dugnadsgjengen.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import no.agens.dugnadsgjengen.ui.screens.tasks.addTask.AddTaskScreen
import no.agens.dugnadsgjengen.ui.screens.overview.addVoluntary.AddVoluntaryScreen
import no.agens.dugnadsgjengen.ui.screens.overview.OverviewScreen
import no.agens.dugnadsgjengen.ui.screens.baller.BallforbrukScreen
import no.agens.dugnadsgjengen.ui.screens.baller.addTeam.AddTeamScreen
import no.agens.dugnadsgjengen.ui.screens.tasks.TasksScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DgNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = Overview.route) {
            OverviewScreen()
        }
        composable(route = Baller.route) {
            BallforbrukScreen()
        }
        composable(route = Tasks.route) {
            TasksScreen()
        }
        composable(route = AddVoluntary.route) {
            AddVoluntaryScreen(
                navController = navController
            )
        }
        composable(route = AddTask.route) {
            AddTaskScreen(
                navController = navController
            )
        }
        composable(route = AddTeam.route) {
            AddTeamScreen(
                navController = navController
            )
        }
    }
}