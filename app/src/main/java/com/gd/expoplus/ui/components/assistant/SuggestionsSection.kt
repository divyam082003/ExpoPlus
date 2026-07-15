package com.gd.expoplus.ui.components.assistant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.CompareArrows
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gd.expoplus.data.model.ai.SuggestionGenerator

@Composable
fun SuggestionsSection(
    onSuggestionClick: (String) -> Unit
) {

    val suggestions = remember {

        SuggestionGenerator.getSuggestions()
    }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Suggested Questions",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        suggestions.forEach { suggestion ->

            SuggestionCard(

                icon = suggestion.icon,

                iconTint = suggestion.iconTint,

                title = suggestion.title

            ) {

                onSuggestionClick(suggestion.title)
            }
        }
    }

}