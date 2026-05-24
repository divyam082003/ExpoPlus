package com.gd.expoplus.data.repository

import com.gd.expoplus.data.local.dao.TransactionDao
import com.gd.expoplus.data.local.entity.TransactionEntity

class TransactionRepository(
    private val dao: TransactionDao
)
{

    suspend fun insertTransaction(
        transaction: TransactionEntity
    ) {
        dao.insertTransaction(transaction)
    }

    suspend fun getAllTransactionIds():
            List<String> {

        return dao.getAllTransactionIds()
    }

    suspend fun getSyncedTransactions():
            List<TransactionEntity> {

        return dao.getSyncedTransactions()
    }

    suspend fun insertAllTransactions(
        transactions: List<TransactionEntity>
    ) {
        dao.insertAllTransactions(transactions)
    }

    fun getActiveTransactions() =
        dao.getActiveTransactions()

    suspend fun softDeleteTransaction(
        transaction: TransactionEntity
    ) {

        dao.updateTransaction(

            transaction.copy(
                isDeleted = true,
                synced = false
            )
        )
    }

    suspend fun getDeletedTransactions():
            List<TransactionEntity> {

        return dao.getDeletedTransactions()
    }

    suspend fun permanentlyDeleteTransaction(
        transaction: TransactionEntity
    ) {

        dao.deleteTransaction(transaction)
    }

    suspend fun deleteAllTransactions() {

        dao.deleteAllTransactions()
    }

    suspend fun getUnsyncedTransactions():
            List<TransactionEntity> {

        return dao.getUnsyncedTransactions()
    }

    suspend fun markAsSynced(id: String) {

        dao.markAsSynced(id)
    }

}