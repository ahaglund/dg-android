package no.agens.dugnadsgjengen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import no.agens.dugnadsgjengen.R
import no.agens.dugnadsgjengen.models.Team
import no.agens.dugnadsgjengen.ui.theme.coal
import no.agens.dugnadsgjengen.ui.theme.ui100
import no.agens.dugnadsgjengen.ui.theme.white

@Composable
fun TeamsListView(
    teams: List<Team>,
    onChangeBallforbruk: (team: Team, numberOfShuttles: Float) -> Unit,
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize(),
    ) {
        for (team in teams.sortedBy { it.name }) {
            item {
                TeamListRow(
                    team = team,
                    onChangeBallforbruk = { onChangeBallforbruk(team, it) }
                )
            }
        }
        item { HeightSpacer(90.dp) }
    }
}

@Composable
fun TeamListRow(
    team: Team,
    onChangeBallforbruk: (numberOfShuttles: Float) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = ui100,
        elevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Title3(
                text = team.name.replaceFirstChar { it.uppercase() },
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 3.dp)
            )
            WidthSpacer(16.dp)
            MinusButton(onClick = { onChangeBallforbruk(team.ballforbruk - 0.5f) })
            Title3(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = team.ballforbruk.toString().replace(".", ","),
            )
            PlusButton(onClick = { onChangeBallforbruk(team.ballforbruk + 0.5f) })
        }
    }
}

@Composable
fun PlusButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = coal)
            .clickable { onClick() },
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = white,
        )
    }
}

@Composable
fun MinusButton(
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = coal)
            .padding(5.dp)
            .clickable { onClick() },

        ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_minus),
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = white,
        )
    }
}