package com.gd.expoplus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gd.expoplus.data.repository.ExpoAssistantRepository
import com.gd.expoplus.data.repository.TransactionRepository

class ExpoAssistantViewModelFactory(

    private val assistantRepository: ExpoAssistantRepository,

    private val transactionRepository: TransactionRepository

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(ExpoAssistantViewModel::class.java)) {

            return ExpoAssistantViewModel(

                assistantRepository,

                transactionRepository

            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}