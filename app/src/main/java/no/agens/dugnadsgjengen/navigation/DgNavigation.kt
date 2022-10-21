package no.agens.dugnadsgjengen.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DgNavigation(
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
            Overview.screen()
        }
        composable(route = Settings.route) {
            Settings.screen()
        }
        composable(route = Tasks.route) {
            Tasks.screen()
        }
        composable(route = AddVoluntary.route) {
            AddVoluntary.screen()
        }
    }
}