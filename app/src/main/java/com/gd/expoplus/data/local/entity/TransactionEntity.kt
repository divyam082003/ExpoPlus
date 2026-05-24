package com.gd.expoplus.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "transactions")
data class TransactionEntity(

    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val type: String,
    val category: String,
    val amount: Double,
    val note: String,
    val date: Long,
    val synced: Boolean = false,
    val isDeleted: Boolean = false
)