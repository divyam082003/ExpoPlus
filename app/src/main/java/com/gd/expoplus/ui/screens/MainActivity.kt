package com.gd.expoplus.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gd.expoplus.data.repository.ExpoAssistantRepository
import com.gd.expoplus.data.repository.SyncStateRepository
import com.gd.expoplus.data.repository.TransactionRepository
import com.gd.expoplus.ui.navigation.Screen
import com.gd.expoplus.ui.theme.ExpoPlusTheme
import com.gd.expoplus.utils.DatabaseProvider
import com.gd.expoplus.viewmodel.ExpoAssistantViewModel
import com.gd.expoplus.viewmodel.ExpoAssistantViewModelFactory
import com.gd.expoplus.viewmodel.TransactionViewModel
import com.gd.expoplus.viewmodel.TransactionViewModelFactory
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ExpoPlusTheme {
                var showSplash by remember {
                    mutableStateOf(true)
                }

                LaunchedEffect(Unit) {

                    delay(2000)

                    showSplash = false
                }

                if (showSplash) {

                    SplashScreen()

                }
                else {

                    val database = DatabaseProvider.getDatabase(this)
                    val repository = TransactionRepository(database.transactionDao())
                    val syncStateRepository = SyncStateRepository(database.syncStateDao())
                    val assistantRepository = ExpoAssistantRepository()


                    val viewModel: TransactionViewModel = viewModel(
                        factory = TransactionViewModelFactory(repository,syncStateRepository)
                    )

                    val assistantViewModel: ExpoAssistantViewModel = viewModel(

                        factory = ExpoAssistantViewModelFactory(

                            assistantRepository = assistantRepository,

                            transactionRepository = repository
                        )
                    )

                    LaunchedEffect(Unit) {
                        viewModel.performFullSync()
                    }

                    val transactions by viewModel
                        .transactions
                        .collectAsStateWithLifecycle()

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {

                        composable(Screen.Home.route) {

                            HomeScreen(
                                transactions = transactions,

                                onAddClick = {

                                    navController.navigate(
                                        Screen.AddExpense.route
                                    )
                                },

                                onManageClick = {

                                    navController.navigate(
                                        Screen.Manage.route
                                    )
                                },

                                onAssistantClick = {
                                    navController.navigate(Screen.ExpoAssistant.route)
                                },


                                onDeleteTransaction = { transaction ->
                                    viewModel.deleteTransaction(transaction)
                                },

                                onAnalyticsClick = {

                                    navController.navigate(
                                        Screen.Analytics.route
                                    )
                                }
                            )
                        }
                        composable(Screen.AddExpense.route) {

                            AddExpenseScreen(

                                onSaveClick = { category, amount, note ->

                                    viewModel.insertTransaction(
                                        category = category,
                                        amount = amount,
                                        note = note
                                    )
                                    navController.popBackStack()
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                        composable(Screen.Manage.route) {

                            ManageScreen(

                                onClearAllClick = {

                                    viewModel.deleteAllTransactions()

                                    navController.popBackStack()
                                },
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(Screen.Analytics.route) {

                            AnalyticsScreen(
                                transactions = transactions,
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                        composable(Screen.ExpoAssistant.route) {

                            ExpoAssistantScreen(
                                viewModel = assistantViewModel,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}