package com.gd.expoplus.data.model.ai



data class AssistantRequest(
    val message: String,
    val context: ExpenseContext
)