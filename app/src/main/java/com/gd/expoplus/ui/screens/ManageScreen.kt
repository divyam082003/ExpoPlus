package com.gd.expoplus.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gd.expoplus.ui.components.SettingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageScreen(
    onClearAllClick: () -> Unit,
    onBackClick: () -> Unit
) {

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(

        containerColor = Color(0xFF120704),

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Manage",
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
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF120704)
                )
            )
        }

    )
    { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        )
        {
            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Features",
                color = Color(0xFF63E65C),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E1E1E)
                )
            ) {

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {

                    SettingItem(

                        icon = Icons.Default.DeleteForever,

                        title = "Delete All Transactions",

                        subtitle = "Remove all saved expense history",

                        onClick = {

                            showDeleteDialog = true
                        },
                        showArrow = false
                    )

                    SettingItem(

                        icon = Icons.Default.FileUpload,
                        title = "Export Data",
                        subtitle = "Export your all transactions",
                        onClick = { },
                        showArrow = false
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Preferences",
                color = Color(0xFF63E65C),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E1E1E)
                )
            ) {

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {

                    SettingItem(

                        icon = Icons.Default.DarkMode,

                        title = "Dark Theme",

                        subtitle = "Current appearance mode",

                        onClick = { }
                    )

                    SettingItem(

                        icon = Icons.Default.Notifications,

                        title = "Notifications",

                        subtitle = "Manage reminders and alerts",

                        onClick = { }
                    )

                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "About",
                color = Color(0xFF63E65C),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E1E1E)
                )
            ) {

                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {

                    SettingItem(

                        icon = Icons.Default.Info,

                        title = "App Version",

                        subtitle = "ExpoPlus v1.0",

                        onClick = { },
                        showArrow = false
                    )



                    SettingItem(

                        icon = Icons.Default.Code,
                        title = "Developer",
                        subtitle = "Built with Jetpack Compose",
                        onClick = { },
                        showArrow = false
                    )
                }
            }
        }
    }





    if (showDeleteDialog) {

        AlertDialog(

            onDismissRequest = {

                showDeleteDialog = false
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        showDeleteDialog = false

                        onClearAllClick()
                    }
                ) {

                    Text(
                        text = "Delete",
                        color = Color.Red
                    )
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {

                        showDeleteDialog = false
                    }
                ) {

                    Text("Cancel")
                }
            },

            title = {

                Text(
                    text = "Delete All Transactions"
                )
            },

            text = {

                Text(
                    text = "This action cannot be undone."
                )
            }
        )
    }
}