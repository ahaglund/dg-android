package no.agens.dugnadsgjengen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import no.agens.dugnadsgjengen.DgConstants
import no.agens.dugnadsgjengen.R
import no.agens.dugnadsgjengen.models.DgTask
import no.agens.dugnadsgjengen.models.Voluntary
import no.agens.dugnadsgjengen.ui.theme.errorBg
import no.agens.dugnadsgjengen.ui.theme.success
import no.agens.dugnadsgjengen.ui.theme.successBg

@Composable
fun VoluntaryTable(
    voluntaries: List<Voluntary>,
    onVoluntaryClicked: (Voluntary) -> Unit,
    dgTasks: List<DgTask>,
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize(),
    ) {
        for (voluntary in voluntaries) {
            item {
                val dgTask = dgTasks.firstOrNull { it.name == voluntary.dgTask }
                VoluntaryTableRow(
                    voluntary = voluntary,
                    dgTask = dgTask ?: DgConstants.DEFAULT_TASK_LEDIG,
                    onVoluntaryClicked = onVoluntaryClicked
                )
            }
        }
        item { HeightSpacer(90.dp) }
    }
}

@Composable
fun VoluntaryTableRow(
    voluntary: Voluntary,
    dgTask: DgTask,
    onVoluntaryClicked: (Voluntary) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 12.dp)
            .clickable { onVoluntaryClicked(voluntary) },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(dgTask.colorHash),
        elevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatusIcon(dgTask)
            WidthSpacer(16.dp)
            VoluntaryTableRowText(
                voluntary = voluntary,
                modifier = Modifier.weight(1f)
            )
//            BodySmall(
//                text = voluntary.phoneNumber ?: ""
//            )

        }
    }
}

@Composable
private fun VoluntaryTableRowText(
    modifier: Modifier = Modifier,
    voluntary: Voluntary,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Title3(
            text = voluntary.name,
            modifier = Modifier.padding(bottom = 3.dp)
        )
        BodySmall(text = voluntary.dgTask.replaceFirstChar { it.uppercase() })
    }
}

@Composable
private fun StatusIcon(
    voluntaryState: String,
) {
    Box(
        modifier = Modifier
            .background(
                color = if (voluntaryState == "ledig") successBg else errorBg,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        if (voluntaryState == "ledig") {
            Icon(
                painter = painterResource(id = R.drawable.ic_checkmark),
                tint = success,
                contentDescription = null
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_x_sign),
                tint = no.agens.dugnadsgjengen.ui.theme.error,
                contentDescription = null
            )
        }
    }
}