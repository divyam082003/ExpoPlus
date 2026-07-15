package com.gd.expoplus.data.model
data class ChatMessage(
    val id: Long = System.currentTimeMillis(),
    val message: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)