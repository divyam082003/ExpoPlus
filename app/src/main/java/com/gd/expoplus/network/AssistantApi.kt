package com.gd.expoplus.network

import com.gd.expoplus.data.model.ai.AssistantRequest
import com.gd.expoplus.data.model.ai.AssistantResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AssistantApi {

    @POST("expo-assistant")
    suspend fun sendMessage(

        @Body request: AssistantRequest

    ): AssistantResponse
}