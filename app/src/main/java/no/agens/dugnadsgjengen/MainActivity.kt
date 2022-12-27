package no.agens.dugnadsgjengen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import no.agens.dugnadsgjengen.navigation.*
import no.agens.dugnadsgjengen.ui.components.DgFab
import no.agens.dugnadsgjengen.ui.components.DugnadsgjengenBottomBar
import no.agens.dugnadsgjengen.ui.components.Title1
import no.agens.dugnadsgjengen.ui.theme.DugnadsgjengenTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Firebase.firestore
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
        val currentScreen = dgAllDestinations.find { it.route == currentDestination?.route }
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        DisposableEffect(systemUiController, useDarkIcons) {
            // Update all of the system bar colors to be transparent, and use
            // dark icons if we're in light theme
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )

            onDispose {}
        }

        Scaffold(
            topBar = {
                Title1(
                    text = currentScreen?.name ?: "",
                    modifier = Modifier
                        .padding(start = 16.dp, top = 40.dp, bottom = 20.dp)
                )
            },
            bottomBar = {
                DugnadsgjengenBottomBar(
                    showBottomBar = dgBottomBarDestinations.contains(currentScreen),
                    allScreens = dgBottomBarDestinations,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen,
                )
            }, floatingActionButton = {
                when (currentScreen) {
                    is Overview -> {
                        DgFab(
                            visible = true,
                            onClick = { navController.navigateSingleTopTo(AddVoluntary.route) },
                        )
                    }
                    is Tasks -> {
                        DgFab(
                            visible = true,
                            onClick = { navController.navigateSingleTopTo(AddTask.route) },
                        )
                    }
                    is Baller -> {
                        DgFab(
                            visible = true,
                            onClick = { navController.navigateSingleTopTo(AddTeam.route) },
                        )
                    }
                }

            }
        ) { innerPadding ->
            DgNavHost(
                navController = navController,
                startDestination = Overview.route,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DugnadsgjengenApp()
}