package no.agens.dugnadsgjengen.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import no.agens.dugnadsgjengen.ui.modifiers.scrollToComponentOnFocus

@Composable
fun InputFieldWithLabel(
    value: String,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    endLabel: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {},
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null,
    scrollState: ScrollState? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardActions: KeyboardActions? = null,
    keyboardOptions: KeyboardOptions? = null,
    maxLength: Int = Int.MAX_VALUE,
) {
    Column {
        val isPressed by interactionSource.collectIsPressedAsState()
        if (isPressed && onClick != null) {
            onClick() // TODO: Why not clickable modifier?
        }
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            readOnly = readOnly,
            modifier = modifier
                .fillMaxWidth()
                .scrollToComponentOnFocus(scrollState),
            interactionSource = interactionSource,
            label = label,
            trailingIcon = endLabel,
            maxLines = 1,
            singleLine = true,
            keyboardOptions = keyboardOptions ?: KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            keyboardActions = keyboardActions ?: KeyboardActions(onDone = null)
        )
    }
}