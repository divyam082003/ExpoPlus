package com.gd.expoplus.ui.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")

    data object AddExpense : Screen("add_expense")
    object Manage : Screen("manage")

    object Analytics : Screen("analytics")
}