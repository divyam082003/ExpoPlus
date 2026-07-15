package com.gd.expoplus.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gd.expoplus.ui.components.assistant.AssistantTopBar
import com.gd.expoplus.ui.components.assistant.ChatInput
import com.gd.expoplus.ui.components.assistant.ChatMessages
import com.gd.expoplus.ui.components.assistant.SuggestionsSection
import com.gd.expoplus.ui.components.assistant.WelcomeSection
import com.gd.expoplus.viewmodel.ExpoAssistantViewModel

@Composable
fun ExpoAssistantScreen(
    viewModel: ExpoAssistantViewModel,
    onBackClick: () -> Unit
) {



    val messages by viewModel.messages.collectAsStateWithLifecycle()
    val isLoading by viewModel
        .isLoading
        .collectAsStateWithLifecycle()

    var message by rememberSaveable {
        mutableStateOf("")
    }

    var showClearDialog by rememberSaveable {
        mutableStateOf(false)
    }

    if (showClearDialog) {

        AlertDialog(

            onDismissRequest = {

                showClearDialog = false
            },

            title = {

                Text(
                    text = "Clear Chat"
                )
            },

            text = {

                Text(
                    text = "This will permanently remove all messages in this conversation."
                )
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        viewModel.clearChat()

                        showClearDialog = false
                    }

                ) {

                    Text("Clear")
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {

                        showClearDialog = false
                    }

                ) {

                    Text("Cancel")
                }
            }
        )
    }

    Scaffold(

        containerColor = Color(0xFF120704),

        topBar = {

            AssistantTopBar(

                onBackClick = onBackClick,
                clearEnabled = messages.isNotEmpty(),
                onClearChatClick = {
                    showClearDialog = true
                }
            )
        },

        bottomBar = {

            ChatInput(

                value = message,

                onValueChange = {
                    message = it
                },

                enabled = !isLoading,

                onSendClick = {

                    viewModel.sendMessage(message)

                    message = ""
                }
            )
        }

    ) { paddingValues ->

        if (messages.isEmpty()) {

            LazyColumn(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp),

                verticalArrangement = Arrangement.Center

            ) {

                item {

                    WelcomeSection()

                    Spacer(
                        modifier = Modifier.height(36.dp)
                    )
                }

                item {

                    SuggestionsSection(

                        onSuggestionClick = {

                            message = it

                            viewModel.sendMessage(message)

                            message = ""
                        }
                    )
                }

                item {

                    Spacer(
                        modifier = Modifier.height(100.dp)
                    )
                }
            }

        } else {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)

            ) {

                ChatMessages(
                    messages = messages
                )
            }
        }
    }
}