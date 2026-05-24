package com.gd.expoplus.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gd.expoplus.data.local.dao.SyncStateDao
import com.gd.expoplus.data.local.dao.TransactionDao
import com.gd.expoplus.data.local.entity.SyncStateEntity
import com.gd.expoplus.data.local.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class,
        SyncStateEntity::class
               ],
    version = 5,
    exportSchema = false
)
abstract class ExpoDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    abstract fun syncStateDao(): SyncStateDao
}