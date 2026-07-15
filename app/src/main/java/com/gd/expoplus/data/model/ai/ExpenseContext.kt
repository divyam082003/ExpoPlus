package com.gd.expoplus.data.model.ai

import kotlinx.serialization.Serializable

@Serializable
data class ExpenseContext(

    val currency: String,

    val totalExpense: Double,

    val totalTransactions: Int,

    val averageExpense: Double,

    val highestExpense: Double,

    val lowestExpense: Double,

    val monthlyExpense: Double,

    val todayExpense: Double,

    val yesterdayExpense: Double,

    val thisWeekExpense: Double,

    val lastWeekExpense: Double,

    val lastMonthExpense: Double,

    val monthlyChangePercent: Double,

    val weeklyChangePercent: Double,

    val highestCategory: String,

    val categoryBreakdown: Map<String, Double>,

    val categoryFrequency: Map<String, Int>,

    val topCategories: List<String>,

    val recentTransactions: List<String>,

    val summary: String
)