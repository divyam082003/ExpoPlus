package com.gd.expoplus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gd.expoplus.data.model.ChatMessage
import com.gd.expoplus.data.model.ai.ExpenseContextBuilder
import com.gd.expoplus.data.repository.ExpoAssistantRepository
import com.gd.expoplus.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExpoAssistantViewModel(

    private val assistantRepository: ExpoAssistantRepository,

    private val transactionRepository: TransactionRepository

) : ViewModel() {

    private val _messages =
        MutableStateFlow<List<ChatMessage>>(emptyList())

    private val _isLoading = MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading.asStateFlow()

    val messages: StateFlow<List<ChatMessage>> =
        _messages.asStateFlow()

    fun sendMessage(message: String) {

        if (message.isBlank()) return

        val currentTime = System.currentTimeMillis()

        val userMessage = ChatMessage(

            id = currentTime,

            message = message,

            isUser = true,

            timestamp = currentTime
        )

        val thinkingMessage = ChatMessage(

            id = currentTime + 1,

            message = "Thinking...",

            isUser = false,

            timestamp = currentTime + 1
        )

        _messages.value =
            _messages.value + userMessage + thinkingMessage

        _isLoading.value = true

        viewModelScope.launch {

            try {

                val transactions =
                    transactionRepository.getAllActiveTransactions()

                val context =
                    ExpenseContextBuilder.build(transactions)

                val reply =
                    assistantRepository.askAssistant(
                        message = message,
                        context = context
                    )

                val updated =
                    _messages.value
                        .dropLast(1)
                        .toMutableList()

                updated.add(

                    ChatMessage(

                        id = System.currentTimeMillis(),

                        message = reply,

                        isUser = false,

                        timestamp = System.currentTimeMillis()
                    )
                )

                _isLoading.value = false
                _messages.value = updated

            } catch (e: Exception) {

                val updated =
                    _messages.value
                        .dropLast(1)
                        .toMutableList()

                updated.add(

                    ChatMessage(

                        id = System.currentTimeMillis(),

                        message = "Sorry, something went wrong. Please try again.",

                        isUser = false,

                        timestamp = System.currentTimeMillis()
                    )
                )

                _isLoading.value = false
                _messages.value = updated
            }
        }
    }

    fun clearChat() {

        _messages.value = emptyList()
    }
}