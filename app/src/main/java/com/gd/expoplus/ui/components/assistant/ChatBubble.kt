package com.gd.expoplus.ui.components.assistant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gd.expoplus.data.model.ChatMessage

@Composable
fun ChatBubble(
    message: ChatMessage
)
{

    Column(

        modifier = Modifier.fillMaxWidth(),

        horizontalAlignment =
            if (message.isUser)
                Alignment.End
            else
                Alignment.Start

    ) {

        Surface(

            color =
                if (message.isUser)
                    Color(0xFF63E65C)
                else
                    Color(0xFF1E1E1E),

            shape = RoundedCornerShape(18.dp),

            modifier = Modifier.widthIn(max = 300.dp)

        ) {

            if (!message.isUser && message.message == "Thinking...") {

                ThinkingIndicator()

            } else {

                Text(

                    text = message.message,

                    modifier = Modifier.padding(14.dp),

                    color =
                        if (message.isUser)
                            Color.Black
                        else
                            Color.White,

                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}