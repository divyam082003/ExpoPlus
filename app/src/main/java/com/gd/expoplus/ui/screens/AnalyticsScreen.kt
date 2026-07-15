package com.gd.expoplus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gd.expoplus.data.local.entity.TransactionEntity
import com.gd.expoplus.ui.components.SettingItem
import com.gd.expoplus.ui.components.charts.CategoryExpense
import com.gd.expoplus.ui.components.charts.ExpenseLineChart
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    transactions: List<TransactionEntity>,
    onBackClick: () -> Unit
)
{

    val calendar = Calendar.getInstance()

    val totalExpense =
        transactions.sumOf { it.amount }

    val totalTransactions =
        transactions.size

    val averageExpense =
        if (transactions.isNotEmpty()) {
            totalExpense / transactions.size
        } else {
            0.0
        }

    val highestCategory =
        transactions
            .groupBy { it.category }
            .mapValues { entry ->
                entry.value.sumOf { it.amount }
            }
            .maxByOrNull { it.value }
            ?.key ?: "-"

    val currentMonth =
        calendar.get(Calendar.MONTH)

    val currentYear =
        calendar.get(Calendar.YEAR)

    val currentMonthName = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(calendar.time)

    val monthlyCategoryTotals =
        transactions
            .filter {

                val cal =
                    Calendar.getInstance().apply {

                        timeInMillis = it.date
                    }

                cal.get(Calendar.YEAR) == currentYear &&
                        cal.get(Calendar.MONTH) == currentMonth
            }
            .groupBy { it.category }
            .mapValues { entry ->

                entry.value.sumOf { it.amount }
            }

    val monthLabels = listOf(

        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    )

    val monthlyExpenseData =
        MutableList(12) { 0f }

    transactions.forEach { transaction ->

        val transactionCalendar =
            Calendar.getInstance().apply {

                timeInMillis = transaction.date
            }

        val year =
            transactionCalendar.get(Calendar.YEAR)

        val month =
            transactionCalendar.get(Calendar.MONTH)

        if (year == currentYear) {

            monthlyExpenseData[month] +=
                transaction.amount.toFloat()
        }
    }


    Scaffold(

        containerColor = Color(0xFF120704),

        topBar =
            {

            TopAppBar(

                title = {

                    Text(
                        text = "Analytics",
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            //Grid
            item {

                LazyVerticalGrid(

                    columns = GridCells.Fixed(2),

                    horizontalArrangement = Arrangement.spacedBy(12.dp),

                    verticalArrangement = Arrangement.spacedBy(12.dp),

                    modifier = Modifier.height(190.dp)

                ) {

                    item {

                        AnalyticsSummaryCard(

                            title = "Expense",

                            value = "₹${totalExpense.toInt()}",

                            icon = Icons.Default.AccountBalanceWallet,

                            cardColor = Color(0xFF1E3A5F)
                        )
                    }

                    item {

                        AnalyticsSummaryCard(

                            title = "Transactions",

                            value = totalTransactions.toString(),

                            icon = Icons.Default.Receipt,

                            cardColor = Color(0xFF3B2A5A)
                        )
                    }

                    item {

                        AnalyticsSummaryCard(

                            title = "Average",

                            value = "₹${averageExpense.toInt()}",

                            icon = Icons.Default.Analytics,

                            cardColor = Color(0xFF204B3A)
                        )
                    }

                    item {

                        AnalyticsSummaryCard(

                            title = "Top Category",

                            value = highestCategory,

                            icon = Icons.Default.Star,

                            cardColor = Color(0xFF5A3A1E)
                        )
                    }
                }
            }

            // line chart
            item {

                Card(

                    shape = RoundedCornerShape(24.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E1E1E)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween,

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Expense Trend",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = currentMonthName,
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        ExpenseLineChart(

                            values = monthlyExpenseData,

                            labels = monthLabels
                        )
                    }
                }
            }

            // category wise expense
            item {

                Column {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Category Analysis",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = currentMonthName,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    CategoryExpense(
                        categoryTotals = monthlyCategoryTotals
                    )
                }
            }


            // external dashboard
            item {

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

                            icon = Icons.Default.ShowChart,

                            title = "Detailed Dashboard",

                            subtitle = "Open advanced analytics in browser",

                            onClick = {

                            },
                            iconTint = Color(0xFF4FC3F7)
                        )
                    }
                }
            }


        }
    }
}


@Composable
fun AnalyticsSummaryCard(

    title: String,

    value: String,

    icon: ImageVector,

    cardColor: Color
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp),

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.12f)),

                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {

                Text(
                    text = title,
                    color = Color.White.copy(alpha = 0.75f),
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = value,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}