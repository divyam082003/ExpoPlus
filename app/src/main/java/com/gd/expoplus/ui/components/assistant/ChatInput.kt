package com.gd.expoplus.ui.components.assistant

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun ChatInput(

    value: String,

    onValueChange: (String) -> Unit,

    enabled: Boolean,

    onSendClick: () -> Unit

) {

    TextField(

        value = value,

        onValueChange = onValueChange,

        minLines = 1,
        maxLines = 5,

        enabled = enabled,

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp, bottom = 20.dp),

        placeholder = {

            Text(
                text = "Ask Expo Assistant...",
                color = Color.Gray
            )
        },

        colors = TextFieldDefaults.colors(

            focusedContainerColor = Color(0xFF120704),

            unfocusedContainerColor = Color(0xFF120704),

            disabledContainerColor = Color(0xFF120704),

            focusedIndicatorColor = Color.Transparent,

            unfocusedIndicatorColor = Color.Transparent,

            disabledIndicatorColor = Color.Transparent,

            cursorColor = Color.White
        ),

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Send
        ),

        keyboardActions = KeyboardActions(

            onSend = {

                if (enabled && value.isNotBlank()) {

                    onSendClick()
                }
            }
        ),

        trailingIcon = {

            IconButton(

                enabled = enabled && value.isNotBlank(),

                onClick = onSendClick

            ) {

                Icon(

                    imageVector = Icons.Default.ArrowUpward,

                    contentDescription = null,

                    tint = if (enabled && value.isNotBlank())
                        Color(0xFF4CAF50)
                    else
                        Color.Gray
                )
            }
        }
    )
}