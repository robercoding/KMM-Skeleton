package app.skeleton.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PlaceInputField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    isEnabled: Boolean,
    onQueryChanged: (String) -> Unit,
    onFocused: () -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor by animateColorAsState(if (isFocused) MaterialTheme.colorScheme.primary else Color.Transparent)
    var internalText by remember { mutableStateOf(text) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        androidx.compose.material3.TextField(
            shape = RoundedCornerShape(8.dp),
            enabled = isEnabled,
            value = internalText,
            textStyle = MaterialTheme.typography.bodyLarge,
            onValueChange = {
                internalText = it
                onQueryChanged(it)
            },
            placeholder = {
                Text(
                    text = hint,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            modifier = Modifier
                .weight(1f)
                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    if (focusState.isFocused) {
                        onFocused()
                    }
                },
            trailingIcon = trailingIcon,
            colors = TextFieldDefaults.colors(
                // focusedTextColor = MaterialTheme.colorScheme.onSurface,
                // unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                // disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                // errorTextColor = MaterialTheme.colorScheme.error,
                // focusedContainerColor = MaterialTheme.colorScheme.surface,
                // unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                // disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                // errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                cursorColor = MaterialTheme.colorScheme.primary,
                errorCursorColor = MaterialTheme.colorScheme.error,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                // focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                // unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
        )
    }
}