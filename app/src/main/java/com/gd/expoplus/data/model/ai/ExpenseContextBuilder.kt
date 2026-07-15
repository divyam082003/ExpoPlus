package com.gd.expoplus.data.model.ai

import com.gd.expoplus.data.local.entity.TransactionEntity
import java.util.Calendar


object ExpenseContextBuilder {

    fun build(
        transactions: List<TransactionEntity>
    ): ExpenseContext {

        val expenses = transactions.filter {

            it.type.equals(
                "Expense",
                true
            )
        }

        if (expenses.isEmpty()) {

            return ExpenseContext(

                currency = "INR",

                totalExpense = 0.0,

                totalTransactions = 0,

                averageExpense = 0.0,

                highestExpense = 0.0,

                lowestExpense = 0.0,

                monthlyExpense = 0.0,

                todayExpense = 0.0,

                yesterdayExpense = 0.0,

                thisWeekExpense = 0.0,

                lastWeekExpense = 0.0,

                lastMonthExpense = 0.0,

                monthlyChangePercent = 0.0,

                weeklyChangePercent = 0.0,

                highestCategory = "None",

                categoryBreakdown = emptyMap(),

                categoryFrequency = emptyMap(),

                topCategories = emptyList(),

                recentTransactions = emptyList(),

                summary = "No expense data available."
            )
        }

        val now = Calendar.getInstance()

        val currentMonth = now.get(Calendar.MONTH)
        val currentYear = now.get(Calendar.YEAR)

        val currentWeek = now.get(Calendar.WEEK_OF_YEAR)

        val todayDay = now.get(Calendar.DAY_OF_YEAR)

        val categoryTotals =
            expenses
                .groupBy { it.category }
                .mapValues {

                    it.value.sumOf {
                            transaction ->
                        transaction.amount
                    }
                }

        val categoryFrequency =
            expenses
                .groupingBy {
                    it.category
                }
                .eachCount()

        val highestCategory =
            categoryTotals.maxByOrNull {
                it.value
            }?.key ?: "None"

        val totalExpense =
            expenses.sumOf {
                it.amount
            }

        val averageExpense =
            totalExpense / expenses.size

        val highestExpense =
            expenses.maxOf {
                it.amount
            }

        val lowestExpense =
            expenses.minOf {
                it.amount
            }

        var monthlyExpense = 0.0
        var lastMonthExpense = 0.0

        var todayExpense = 0.0
        var yesterdayExpense = 0.0

        var thisWeekExpense = 0.0
        var lastWeekExpense = 0.0

        expenses.forEach {

            val calendar =
                Calendar.getInstance()

            calendar.timeInMillis = it.date

            val month =
                calendar.get(Calendar.MONTH)

            val year =
                calendar.get(Calendar.YEAR)

            val week =
                calendar.get(Calendar.WEEK_OF_YEAR)

            val day =
                calendar.get(Calendar.DAY_OF_YEAR)

            if (
                month == currentMonth &&
                year == currentYear
            ) {

                monthlyExpense += it.amount
            }

            if (
                month == currentMonth - 1 &&
                year == currentYear
            ) {

                lastMonthExpense += it.amount
            }

            if (
                week == currentWeek &&
                year == currentYear
            ) {

                thisWeekExpense += it.amount
            }

            if (
                week == currentWeek - 1 &&
                year == currentYear
            ) {

                lastWeekExpense += it.amount
            }

            if (
                day == todayDay &&
                year == currentYear
            ) {

                todayExpense += it.amount
            }

            if (
                day == todayDay - 1 &&
                year == currentYear
            ) {

                yesterdayExpense += it.amount
            }
        }

        val monthlyChange =

            if (lastMonthExpense == 0.0)
                0.0
            else
                ((monthlyExpense - lastMonthExpense)
                        / lastMonthExpense) * 100

        val weeklyChange =

            if (lastWeekExpense == 0.0)
                0.0
            else
                ((thisWeekExpense - lastWeekExpense)
                        / lastWeekExpense) * 100

        val topCategories =
            categoryTotals

                .toList()

                .sortedByDescending {

                    it.second
                }

                .take(5)

                .map {

                    "${it.first}: ₹${it.second.toInt()}"
                }

        val recentTransactions =
            expenses

                .sortedByDescending {

                    it.date
                }

                .take(5)

                .map {

                    "${it.category} ₹${it.amount.toInt()}"
                }

        val summary = buildString {

            append("Total expense ₹${totalExpense.toInt()}. ")

            append("Transactions ${expenses.size}. ")

            append("Highest category $highestCategory. ")

            append("This month ₹${monthlyExpense.toInt()}. ")

            append("Today ₹${todayExpense.toInt()}. ")
        }

        return ExpenseContext(

            currency = "INR",

            totalExpense = totalExpense,

            totalTransactions = expenses.size,

            averageExpense = averageExpense,

            highestExpense = highestExpense,

            lowestExpense = lowestExpense,

            monthlyExpense = monthlyExpense,

            todayExpense = todayExpense,

            yesterdayExpense = yesterdayExpense,

            thisWeekExpense = thisWeekExpense,

            lastWeekExpense = lastWeekExpense,

            lastMonthExpense = lastMonthExpense,

            monthlyChangePercent = monthlyChange,

            weeklyChangePercent = weeklyChange,

            highestCategory = highestCategory,

            categoryBreakdown = categoryTotals,

            categoryFrequency = categoryFrequency,

            topCategories = topCategories,

            recentTransactions = recentTransactions,

            summary = summary
        )
    }
}