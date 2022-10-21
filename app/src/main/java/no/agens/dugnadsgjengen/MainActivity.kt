package no.agens.dugnadsgjengen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import no.agens.dugnadsgjengen.navigation.*
import no.agens.dugnadsgjengen.ui.components.DgFab
import no.agens.dugnadsgjengen.ui.components.DugnadsgjengenBottomBar
import no.agens.dugnadsgjengen.ui.theme.DugnadsgjengenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DugnadsgjengenApp()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DugnadsgjengenApp() {
    DugnadsgjengenTheme {
        val navController = rememberAnimatedNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = dgBottomBarDestinations.find { it.route == currentDestination?.route }

        Scaffold(bottomBar = {
            DugnadsgjengenBottomBar(
                showBottomBar = dgBottomBarDestinations.contains(currentScreen),
                allScreens = dgBottomBarDestinations,
                onTabSelected = { newScreen ->
                    navController.navigateSingleTopTo(newScreen.route)
                },
                currentScreen = currentScreen,
            )
        }, floatingActionButton = {
            DgFab(
                visible = currentScreen is Overview,
                onClick = { navController.navigateSingleTopTo(AddVoluntary.route) },
            )
        }) { innerPadding ->
            DgNavigation(
                navController = navController,
                startDestination = Overview.route,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DugnadsgjengenApp()
}