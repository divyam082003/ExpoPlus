package com.gd.expoplus.ui.components.assistant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeSection() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons.Default.AutoAwesome,
            contentDescription = null,
            tint = Color(0xFF63E65C),
            modifier = Modifier
                .size(42.dp)
                .padding(bottom = 12.dp)
        )

        Text(
            text = "Expo Assistant",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Your AI-powered financial companion",
            color = Color(0xFFBDBDBD),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 6.dp)
        )

        Text(
            text = "Ask anything about your expenses, spending habits, savings, or monthly trends.",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 20.sp,
            modifier = Modifier.padding(
                top = 14.dp,
                start = 8.dp,
                end = 8.dp
            )
        )
    }
}