package com.gd.expoplus.data.repository

import android.util.Log
import com.gd.expoplus.data.model.ai.AssistantRequest
import com.gd.expoplus.data.model.ai.ExpenseContext
import com.gd.expoplus.network.RetrofitClient


class ExpoAssistantRepository {

    suspend fun askAssistant(
        message: String,
        context: ExpenseContext
    ): String {

        return try {

            val response = RetrofitClient
                .assistantApi
                .sendMessage(

                    AssistantRequest(

                        message = message,

                        context = context
                    )
                )
            Log.d("Gemini", "Success: ${response.reply}")

            response.reply

        } catch (e: Exception) {
            Log.e("Gemini", "Request failed", e)

            if (e is retrofit2.HttpException) {

                val errorBody = e.response()?.errorBody()?.string()

                Log.e("Gemini", "HTTP Code: ${e.code()}")

                Log.e("Gemini", "Error Body: $errorBody")
            }

            throw e
        }
    }
}