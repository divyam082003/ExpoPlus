package com.gd.expoplus.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class RemoteTransaction(

    val id: String,
    val type: String,
    val category: String,
    val amount: Double,
    val note: String,
    val date: Long
)

