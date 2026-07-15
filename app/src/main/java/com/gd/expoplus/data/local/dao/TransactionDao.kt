package com.gd.expoplus.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gd.expoplus.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTransactions(
        transactions: List<TransactionEntity>
    )

    @Query("""
    SELECT * FROM transactions
    WHERE isDeleted = 0
    ORDER BY date DESC
""")
    fun getActiveTransactions():
            Flow<List<TransactionEntity>>


    @Query("""
    SELECT * FROM transactions
    WHERE synced = 0
    AND isDeleted = 0
""")
    suspend fun getUnsyncedTransactions():
            List<TransactionEntity>


    @Query("""
    SELECT * FROM transactions
    WHERE synced = 1
    AND isDeleted = 0
""")
    suspend fun getSyncedTransactions():
            List<TransactionEntity>



    @Query("""
    SELECT id FROM transactions
""")
    suspend fun getAllTransactionIds():
            List<String>

    @Query("""
    UPDATE transactions
    SET synced = 1
    WHERE id = :id
""")
    suspend fun markAsSynced(id: String)


    @Update
    suspend fun updateTransaction(
        transaction: TransactionEntity
    )
    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)
    @Query("DELETE FROM transactions")
    suspend fun deleteAllTransactions()


    @Query("""
        SELECT * FROM transactions
        WHERE isDeleted = 1
    """)
    suspend fun getDeletedTransactions():
            List<TransactionEntity>

    @Query("""
    SELECT * FROM transactions
    WHERE isDeleted = 0
    ORDER BY date DESC
""")
    suspend fun getAllActiveTransactions(): List<TransactionEntity>
}