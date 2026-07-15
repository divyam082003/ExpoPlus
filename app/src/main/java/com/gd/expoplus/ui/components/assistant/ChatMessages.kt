package com.gd.expoplus.ui.components.assistant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.gd.expoplus.data.model.ChatMessage


@Composable
fun ChatMessages(
    messages: List<ChatMessage>
) {

    LazyColumn(

        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        items(messages) {

            ChatBubble(it)
        }
    }
}