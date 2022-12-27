package no.agens.dugnadsgjengen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import no.agens.dugnadsgjengen.R
import no.agens.dugnadsgjengen.models.DgTask
import no.agens.dugnadsgjengen.ui.theme.success

@Composable
fun TasksListView(
    tasks: List<DgTask>,
    onTaskClicked: (DgTask) -> Unit,
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize(),
    ) {
        for (task in tasks) {
            item {
                TasksListRow(
                    task = task,
                    onTaskClicked = onTaskClicked
                )
            }
        }
        item { HeightSpacer(90.dp) }
    }
}

@Composable
fun TasksListRow(
    task: DgTask,
    onTaskClicked: (DgTask) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 12.dp)
            .clickable { onTaskClicked(task) },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(task.colorHash),
        elevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatusIcon(task)
            WidthSpacer(16.dp)
            Title3(
                text = task.name.replaceFirstChar { it.uppercase() },
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 3.dp)
            )

        }
    }
}