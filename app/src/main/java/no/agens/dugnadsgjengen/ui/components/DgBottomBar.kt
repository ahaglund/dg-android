package no.agens.dugnadsgjengen.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.agens.dugnadsgjengen.navigation.DgNavDestination

@Composable
fun DugnadsgjengenBottomBar(
    showBottomBar: Boolean,
    allScreens: List<DgNavDestination>,
    onTabSelected: (DgNavDestination) -> Unit,
    currentScreen: DgNavDestination?
) {
    AnimatedVisibility(
        visible = showBottomBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        Column {
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.039f))
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                allScreens.forEach { screen ->
                    BottomBarItem(
                        screen = screen,
                        isSelected = currentScreen == screen,
                        onClick = { onTabSelected(screen) },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBarItem(
    screen: DgNavDestination,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, radius = 48.dp),
                onClick = onClick,
            )
            .fillMaxHeight(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val itemColor by animateColorAsState(
                targetValue = if (isSelected) {
                    MaterialTheme.colors.onSurface
                } else {
                    MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                }
            )

            Icon(
                imageVector = if (isSelected) screen.selectedIcon else screen.icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = itemColor,
            )
            Text(
                text = screen.name,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                color = itemColor,
                textAlign = TextAlign.Center,
            )
        }
    }
}