package com.gd.expoplus.ui.components.charts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CategoryExpense(
    categoryTotals: Map<String, Double>
) {

    val totalExpense =
        categoryTotals.values.sum()

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            categoryTotals
                .toList()
                .sortedByDescending { it.second }
                .forEachIndexed { index, (category, amount) ->

                    val progress =
                        (amount / totalExpense).toFloat()

                    val categoryColor = listOf(

                        Color(0xFF63E65C),
                        Color(0xFF4FC3F7),
                        Color(0xFFFFB74D),
                        Color(0xFFE57373),
                        Color(0xFFBA68C8),
                        Color(0xFFFF8A65)

                    )[index % 6]

                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(

                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(
                                        categoryColor.copy(alpha = 0.15f)
                                    ),

                                contentAlignment = Alignment.Center
                            ) {

                                Icon(

                                    imageVector = getCategoryIcon(category),

                                    contentDescription = null,

                                    tint = categoryColor,

                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),

                                    horizontalArrangement =
                                        Arrangement.SpaceBetween,

                                    verticalAlignment =
                                        Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = category,
                                        color = Color.White,
                                        fontWeight = FontWeight.Medium,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    LinearProgressIndicator(

                                        progress = { progress },

                                        modifier = Modifier
                                            .weight(1f)
                                            .height(6.dp)
                                            .clip(
                                                RoundedCornerShape(20.dp)
                                            ),

                                        color = categoryColor,

                                        trackColor = Color.DarkGray
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(

                                        text = "${(progress * 100).toInt()}%",

                                        color = Color.Gray,

                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                    }
                }
        }
    }
}

fun getCategoryIcon(category: String): ImageVector {

    return when (category) {

        "Food" -> Icons.Default.Fastfood

        "Shopping" -> Icons.Default.ShoppingBag

        "Travel" -> Icons.Default.Flight

        "Bills" -> Icons.Default.Receipt

        "Health" -> Icons.Default.LocalHospital

        "Entertainment" -> Icons.Default.Movie

        "Education" -> Icons.Default.School

        else -> Icons.Default.Payments
    }
}