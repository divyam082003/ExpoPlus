package com.gd.expoplus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gd.expoplus.R
import com.gd.expoplus.data.local.entity.TransactionEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    transactions: List<TransactionEntity>,
    onAddClick: () -> Unit,
    onManageClick: () -> Unit,
    onDeleteTransaction: (TransactionEntity) -> Unit,
    onAnalyticsClick: () -> Unit
)
{

    val totalExpense = transactions.sumOf { it.amount }

    Scaffold(

        containerColor = Color(0xFF120704),

        topBar = {

            TopAppBar(

                title = {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.expo_plus_icon),
                            contentDescription = "Logo",
                            modifier = Modifier.size(36.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "ExpoPlus",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },

                actions = {

                    IconButton(onClick = { }) {

                        Icon(
                            imageVector = Icons.Default.Person4,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF120704)
                )
            )
        },

        bottomBar = {

            Box {

                NavigationBar(

                    containerColor = Color(0xFF1E1E1E),

                    tonalElevation = 8.dp
                ) {

                    NavigationBarItem(

                        selected = false,

                        onClick = onManageClick,

                        icon = {

                            Icon(
                                Icons.Default.ManageAccounts,
                                contentDescription = null
                            )
                        },

                        label = {
                            Text("Manage")
                        }
                    )

                    Box(
                        modifier = Modifier.weight(1f)
                    )

                    NavigationBarItem(

                        selected = false,

                        onClick = onAnalyticsClick,

                        icon = {

                            Icon(
                                Icons.Default.Analytics,
                                contentDescription = null
                            )
                        },

                        label = {
                            Text("Analytics")
                        }
                    )
                }

                FloatingActionButton(

                    onClick = onAddClick,

                    containerColor = Color(0xFF63E65C),

                    contentColor = Color.Black,

                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = (-28).dp)
                        .shadow(
                            elevation = 20.dp,
                            shape = CircleShape,
                            clip = false
                        )
                        .size(68.dp),

                    elevation = FloatingActionButtonDefaults.elevation(

                        defaultElevation = 14.dp,

                        pressedElevation = 18.dp,

                        focusedElevation = 16.dp,

                        hoveredElevation = 16.dp
                    )
                ) {

                    Icon(

                        imageVector = Icons.Default.Add,

                        contentDescription = null,

                        modifier = Modifier.size(34.dp)
                    )
                }
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {

                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF1DB954),
                                    Color(0xFF129C52),
                                    Color(0xFF0B6E3E)
                                )
                            )
                        )
                        .fillMaxWidth()
                ) {

                    // soft accent glow
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .offset(x = 120.dp, y = (-35).dp)
                            .background(
                                Color.White.copy(alpha = 0.07f),
                                shape = CircleShape
                            )
                    )

                    Column(
                        modifier = Modifier.padding(
                            horizontal = 18.dp,
                            vertical = 16.dp
                        )
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Color.White.copy(alpha = 0.16f)
                                    ),

                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Default.AccountBalanceWallet,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))

                            Column {

                                Text(
                                    text = "Total Expense",
                                    color = Color.White.copy(alpha = 0.88f),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Text(
                                    text = "Overall Expenses",
                                    color = Color.White.copy(alpha = 0.65f),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "₹$totalExpense",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(items = transactions,
                    key = { transaction ->
                        transaction.id
                    }) { transaction ->

                    val dismissState = rememberSwipeToDismissBoxState()

                    LaunchedEffect(dismissState.currentValue) {

                        if (dismissState.currentValue ==
                            SwipeToDismissBoxValue.EndToStart
                        ) {

                            onDeleteTransaction(transaction)
                        }
                    }

                    SwipeToDismissBox(

                        state = dismissState,

                        enableDismissFromStartToEnd = false,
                        enableDismissFromEndToStart = true,

                        backgroundContent = {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(Color.Red)
                                    .padding(end = 20.dp),

                                contentAlignment = Alignment.CenterEnd
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }

                    )

                    {

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF1E1E1E)
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),

                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(52.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF2E7D32)),

                                    contentAlignment = Alignment.Center
                                ) {

                                    Icon(
                                        imageVector = getCategoryIcon(transaction.category),
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.width(14.dp))

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Text(
                                        text = "₹${transaction.amount}",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = transaction.note,
                                        color = Color.Gray,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }

                                Text(
                                    text = SimpleDateFormat(
                                        "dd MMM yyyy",
                                        Locale.getDefault()
                                    ).format(Date(transaction.date)),

                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
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