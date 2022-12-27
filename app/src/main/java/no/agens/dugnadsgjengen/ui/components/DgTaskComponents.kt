package no.agens.dugnadsgjengen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import no.agens.dugnadsgjengen.models.DgTask
import no.agens.dugnadsgjengen.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DgTaskButton(
    modifier: Modifier = Modifier,
    onClick: (DgTask) -> Unit,
    task: DgTask,
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .padding(horizontal = 6.dp, vertical = 2.dp),
        backgroundColor = Color(task.colorHash),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        onClick = { onClick(task) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            StatusIconSmall(task)
            WidthSpacer(4.dp)
            Title3(
                text = task.name.replaceFirstChar { it.uppercase() },
                modifier = Modifier.weight(1f),
                maxLines = 1,
            )
        }
    }
}

@Composable
fun StatusIcon(
    task: DgTask
) {
    Title2(
        modifier = Modifier.padding(4.dp),
        text = task.emoji ?: ""
    )
}

@Composable
fun StatusIconSmall(
    task: DgTask
) {
    Box(
        modifier = Modifier
            .background(
                color = Color(task.colorHash),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(2.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Text(text = task.emoji ?: "")
    }
}

@Composable
fun ColorCircle(
    color: Color,
    isSelected: Boolean,
    onColorSelected: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(30.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = if (isSelected) Color.Black else Color.LightGray,
                shape = CircleShape
            )
            .background(color)
            .clickable{ onColorSelected(color.hashCode()) }
    )
}

@Composable
fun ColorSelector(
    onColorSelected: (Int) -> Unit,
    selectedColorHash: Int,
) {
    HeightSpacer(height = 24.dp)
    Title3("Velg farge")
    Row(
        modifier = Modifier.padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ColorCircle(
            color = successBg,
            isSelected = selectedColorHash == successBg.hashCode(),
            onColorSelected = { onColorSelected(it) }
        )
        ColorCircle(
            color = errorBg,
            isSelected = selectedColorHash == errorBg.hashCode(),
            onColorSelected = { onColorSelected(it) }
        )
        ColorCircle(
            color = infoBg,
            isSelected = selectedColorHash == infoBg.hashCode(),
            onColorSelected = { onColorSelected(it) }
        )
        ColorCircle(
            color = white,
            isSelected = selectedColorHash == white.hashCode(),
            onColorSelected = { onColorSelected(it) }
        )
        ColorCircle(
            color = ui200,
            isSelected = selectedColorHash == ui200.hashCode(),
            onColorSelected = { onColorSelected(it) }
        )
        ColorCircle(
            color = warningBg,
            isSelected = selectedColorHash == warningBg.hashCode(),
            onColorSelected = { onColorSelected(it) }
        )
        ColorCircle(
            color = Purple200,
            isSelected = selectedColorHash == Purple200.hashCode(),
            onColorSelected = { onColorSelected(it) }
        )
        ColorCircle(
            color = Teal200,
            isSelected = selectedColorHash == Teal200.hashCode(),
            onColorSelected = { onColorSelected(it) }
        )
    }
}