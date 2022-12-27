package no.agens.dugnadsgjengen.ui.modifiers

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import com.google.accompanist.insets.LocalWindowInsets

/**
 * Modifier to animate-scroll to make component visible when keyboard is displayed and component is
 * behind the keyboard.
 *
 * This is a workaround for this Accompanist issue:
 * https://github.com/google/accompanist/issues/813
 */
fun Modifier.scrollToComponentOnFocus(scrollState: ScrollState?) = composed {
    if (scrollState == null) {
        this
    } else {
        val ime = LocalWindowInsets.current.ime
        var yPos: Float by remember { mutableStateOf(0f) }
        var yHeight: Float by remember { mutableStateOf(0f) }
        var isFocused by remember { mutableStateOf(false) }

        LaunchedEffect(ime.bottom) {
            if (ime.isVisible && isFocused) {
                val scrollAmount = yPos - ime.bottom.toFloat()
                if (scrollAmount > 0f) { // Only scroll up if component is _behind_ keyboard
                    scrollState.animateScrollBy(scrollAmount + yHeight)
                }
            }
        }

        this.then(
            Modifier
                .onFocusChanged { isFocused = it.isFocused }
                .onGloballyPositioned {
                    yPos = it.positionInRoot().y
                    yHeight = it.size.height.toFloat()
                }
        )
    }
}
