package no.agens.dugnadsgjengen.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import no.agens.dugnadsgjengen.models.DgTask
import no.agens.dugnadsgjengen.models.Voluntary
import kotlin.math.absoluteValue
import kotlin.math.max

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerTabs(
    pagerState: PagerState,
    pages: List<String>,
    onTabSelected: (tabIndex: Int) -> Unit,
) {
    TabRow(
//        backgroundColor = MaterialTheme.colors.background,
//        edgePadding = 2.dp,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            CustomIndicator(
                Modifier
                    .customPagerTabIndicatorOffset(pagerState, tabPositions)
                    .clip(RoundedCornerShape(topEnd = 5.dp, topStart = 5.dp)),
                color = MaterialTheme.colors.onSurface,
                height = 3.dp
            )
        }
    ) {
        pages.forEachIndexed { index, title ->
            val textAlpha by animateFloatAsState(
                if (index == pagerState.currentPage) 1f else 0.5f
            )
            Tab(
                text = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = title.replaceFirstChar { it.uppercase() },
                        maxLines = 1,
                        overflow = TextOverflow.Visible,
                        color = MaterialTheme.colors.onSurface.copy(alpha = textAlpha),
                        style = MaterialTheme.typography.h5,
                    )
                },
                modifier = Modifier.background(Color.White),
                selected = pagerState.currentPage == index,
                onClick = {
                    onTabSelected(index)
                },
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun VoluntaryPager(
    pagerState: PagerState,
    voluntaries: List<Voluntary>,
    dgTasks: List<DgTask>,
    numPages: Int,
    isLoading: Boolean,
    onVoluntaryClicked: (Voluntary) -> Unit
) {
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
        verticalAlignment = Alignment.Top,
        count = numPages,
    ) { page ->
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Loader(
                    modifier = Modifier.padding(top = 40.dp)
                )
            }

        } else {
            when (page) {
                0 -> {
                    VoluntaryTable(
                        voluntaries = voluntaries.sortedBy { it.name },
                        onVoluntaryClicked = onVoluntaryClicked,
                        dgTasks = dgTasks,
                    )
                }
                1 -> {
                    val filteredVoluntary = voluntaries
                        .filter { it.dgTask == "ledig" }
                        .sortedBy { it.name }
                    if (filteredVoluntary.isEmpty()) {
//                    NoProjectsAddedTableView(text = stringResource(R.string.EmptyPlannedProjectList))
                    } else {
                        VoluntaryTable(
                            voluntaries = filteredVoluntary,
                            onVoluntaryClicked = onVoluntaryClicked,
                            dgTasks = dgTasks,
                        )
                    }
                }
                2 -> {
                    val filteredVoluntary = voluntaries
                        .filter { it.dgTask != "ledig" }
                        .sortedBy { it.name }
                    if (filteredVoluntary.isEmpty()) {
//                    NoProjectsAddedTableView(text = stringResource(R.string.EmptyFinishedProjectList))
                    } else {
                        VoluntaryTable(
                            voluntaries = filteredVoluntary,
                            onVoluntaryClicked = onVoluntaryClicked,
                            dgTasks = dgTasks,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomIndicator(
    modifier: Modifier = Modifier,
    height: Dp = TabRowDefaults.IndicatorHeight,
    color: Color = LocalContentColor.current
) {
    Box(
        modifier
            .height(height)
            .background(color)
    )
}

// FIXME: Is this custom indicator needed? Look into using built in indicator instead
/**
 * This is a modified version of the Modifier.pagerTabIndicatorOffset composable.
 */
@OptIn(ExperimentalPagerApi::class)
fun Modifier.customPagerTabIndicatorOffset(
    pagerState: PagerState,
    tabPositions: List<TabPosition>
): Modifier = composed {
    val targetIndicatorOffset: Dp

    val currentTab = tabPositions[pagerState.currentPage] // Gets
    val targetPage = pagerState.targetPage
    val targetTab = targetPage?.let { tabPositions.getOrNull(it) }

    if (targetTab != null) {
        // The distance between the target and current page. If the pager is animating over many
        // items this could be > 1
        val targetDistance = (targetPage - pagerState.currentPage).absoluteValue
        // Our normalized fraction over the target distance
        val fraction = (pagerState.currentPageOffset / max(targetDistance, 1)).absoluteValue

//        indicatorWidth = currentTab.width / 2
        targetIndicatorOffset =
            lerp(currentTab.left, targetTab.left, fraction) + ((currentTab.width - 36.dp) / 2)
    } else {
        // Otherwise we just use the current tab/page
        targetIndicatorOffset = currentTab.left + ((currentTab.width - 36.dp) / 2)
    }

    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = targetIndicatorOffset)
        .width(36.dp)
}