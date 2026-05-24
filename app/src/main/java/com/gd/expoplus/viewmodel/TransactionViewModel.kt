package com.gd.expoplus.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gd.expoplus.data.local.entity.TransactionEntity
import com.gd.expoplus.data.remote.RemoteTransaction
import com.gd.expoplus.data.remote.SupabaseRepository
import com.gd.expoplus.data.repository.SyncStateRepository
import com.gd.expoplus.data.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val repository: TransactionRepository,
    private val syncStateRepository: SyncStateRepository
) : ViewModel()
{

    private val supabaseRepository = SupabaseRepository()

    val transactions = repository.getActiveTransactions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    fun insertTransaction(
        category: String,
        amount: Double,
        note: String
    )
    {

        viewModelScope.launch(Dispatchers.IO) {

            val transaction = TransactionEntity(
                category = category,
                amount = amount,
                note = note,
                type = "Expense",
                date = System.currentTimeMillis(),
                synced = false
            )

            // ALWAYS SAVE LOCALLY FIRST
            repository.insertTransaction(transaction)

            try {

                val remoteTransaction =
                    RemoteTransaction(
                        id = transaction.id,
                        type = transaction.type,
                        category = transaction.category,
                        amount = transaction.amount,
                        note = transaction.note,
                        date = transaction.date
                    )

                // TRY REMOTE INSERT
                supabaseRepository.insertTransaction(
                    remoteTransaction
                )

                // MARK SYNCED
                repository.markAsSynced(
                    transaction.id
                )

                Log.d(
                    "SYNC",
                    "Inserted + Synced"
                )

            } catch (e: Exception) {

                Log.e(
                    "SYNC",
                    "Pending Sync"
                )
            }
        }
    }

    private suspend fun syncPendingInserts() {

        try {

            val unsyncedTransactions =
                repository.getUnsyncedTransactions()

            unsyncedTransactions.forEach { transaction ->

                try {

                    val remoteTransaction =
                        RemoteTransaction(
                            id = transaction.id,
                            type = transaction.type,
                            category = transaction.category,
                            amount = transaction.amount,
                            note = transaction.note,
                            date = transaction.date
                        )

                    supabaseRepository.insertTransaction(
                        remoteTransaction
                    )

                    repository.markAsSynced(
                        transaction.id
                    )

                    Log.d(
                        "SYNC_INSERT",
                        "Synced: ${transaction.id}"
                    )

                } catch (e: Exception) {

                    Log.e(
                        "SYNC_INSERT",
                        "Retry failed"
                    )
                }
            }

        } catch (e: Exception) {

            Log.e(
                "SYNC_INSERT",
                e.message.toString()
            )
        }
    }

    private suspend fun syncPendingDeletes() {

        try {

            val deletedTransactions =
                repository.getDeletedTransactions()

            deletedTransactions.forEach { transaction ->

                try {

                    supabaseRepository.deleteTransaction(
                        transaction.id
                    )

                    repository.permanentlyDeleteTransaction(
                        transaction
                    )

                    Log.d(
                        "SYNC_DELETE",
                        "Deleted: ${transaction.id}"
                    )

                } catch (e: Exception) {

                    Log.e(
                        "SYNC_DELETE",
                        "Retry failed"
                    )
                }
            }

        } catch (e: Exception) {

            Log.e(
                "SYNC_DELETE",
                e.message.toString()
            )
        }
    }

    private suspend fun syncTransactionsFromSupabase() {

        try {

            val remoteTransactions =
                supabaseRepository.getTransactions()

            val remoteIds =
                remoteTransactions.map { it.id }

            val localIds =
                repository.getAllTransactionIds()

            val newTransactions =
                remoteTransactions.filter {

                    it.id !in localIds
                }

            val localTransactions =
                newTransactions.map {

                    TransactionEntity(
                        id = it.id,
                        type = it.type,
                        category = it.category,
                        amount = it.amount,
                        note = it.note,
                        date = it.date,
                        synced = true
                    )
                }

            repository.insertAllTransactions(
                localTransactions
            )

            val syncedLocalTransactions =
                repository.getSyncedTransactions()

            syncedLocalTransactions.forEach { transaction ->

                if (transaction.id !in remoteIds) {

                    repository.permanentlyDeleteTransaction(
                        transaction
                    )

                    Log.d(
                        "SYNC_DELETE_REMOTE",
                        "Removed locally: ${transaction.id}"
                    )
                }
            }

            Log.d(
                "SYNC_FETCH",
                "Sync Complete"
            )

        } catch (e: Exception) {

            Log.e(
                "SYNC_FETCH",
                e.message.toString()
            )
        }
    }


    fun deleteTransaction(
        transaction: TransactionEntity
    ) {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                // SOFT DELETE LOCALLY
                repository.softDeleteTransaction(
                    transaction
                )

                try {

                    // DELETE FROM SUPABASE
                    supabaseRepository.deleteTransaction(
                        transaction.id
                    )

                    // PERMANENT DELETE LOCAL
                    repository.permanentlyDeleteTransaction(
                        transaction
                    )

                } catch (e: Exception) {

                    Log.e(
                        "SUPABASE_DELETE",
                        "Will retry later"
                    )
                }

            } catch (e: Exception) {

                Log.e(
                    "DELETE",
                    e.message.toString()
                )
            }
        }
    }
    fun deleteAllTransactions() {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                // DELETE REMOTE
                supabaseRepository
                    .deleteAllTransactions()

                // DELETE LOCAL
                repository.deleteAllTransactions()

                // RESET FLAG
                syncStateRepository
                    .setPendingDeleteAll(false)

                Log.d(
                    "DELETE_ALL",
                    "All Deleted"
                )

            } catch (e: Exception) {

                // DELETE LOCAL ANYWAY
                repository.deleteAllTransactions()

                // SAVE PENDING STATE
                syncStateRepository
                    .setPendingDeleteAll(true)

                Log.e(
                    "DELETE_ALL",
                    "Pending Remote Delete"
                )
            }
        }
    }

    private suspend fun syncPendingDeleteAll() {

        try {

            val pendingDeleteAll =
                syncStateRepository
                    .isPendingDeleteAll()

            if (pendingDeleteAll) {

                supabaseRepository
                    .deleteAllTransactions()

                syncStateRepository
                    .setPendingDeleteAll(false)

                Log.d(
                    "SYNC_DELETE_ALL",
                    "Completed"
                )
            }

        } catch (e: Exception) {

            Log.e(
                "SYNC_DELETE_ALL",
                "Retry failed"
            )
        }
    }

    fun performFullSync() {
        viewModelScope.launch(Dispatchers.IO) {
            syncPendingDeleteAll()
            syncPendingInserts()
            syncPendingDeletes()
            syncTransactionsFromSupabase()
        }
    }
}

class TransactionViewModelFactory(
    private val repository: TransactionRepository,
    private val syncStateRepository: SyncStateRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return TransactionViewModel(repository,syncStateRepository) as T
    }
}