package com.gd.expoplus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gd.expoplus.data.local.entity.SyncStateEntity

@Dao
interface SyncStateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSyncState(
        syncState: SyncStateEntity
    )

    @Query("""
        SELECT * FROM sync_state
        WHERE id = 1
    """)
    suspend fun getSyncState():
            SyncStateEntity?
}