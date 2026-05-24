package com.gd.expoplus.data.repository

import com.gd.expoplus.data.local.dao.SyncStateDao
import com.gd.expoplus.data.local.entity.SyncStateEntity

class SyncStateRepository(
    private val dao: SyncStateDao
) {

    suspend fun setPendingDeleteAll(
        pending: Boolean
    ) {

        dao.insertSyncState(
            SyncStateEntity(
                pendingDeleteAll = pending
            )
        )
    }

    suspend fun isPendingDeleteAll():
            Boolean {

        return dao.getSyncState()
            ?.pendingDeleteAll
            ?: false
    }
}