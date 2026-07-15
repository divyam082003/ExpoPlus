package com.gd.expoplus.ui.components.assistant

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DeleteSweep
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistantTopBar(
    onBackClick: () -> Unit,
    onClearChatClick: () -> Unit = {},
    clearEnabled: Boolean
) {

    TopAppBar(

        title = {

            Text(
                text = "Expo Assistant",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },

        navigationIcon = {

            IconButton(
                onClick = onBackClick
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },

        actions = {

            IconButton(
                enabled = clearEnabled,
                onClick = onClearChatClick
            ) {

                Icon(
                    imageVector = Icons.Outlined.DeleteSweep,
                    contentDescription = "Clear Chat",
                    tint = Color.White
                )
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF120704)
        )
    )
}