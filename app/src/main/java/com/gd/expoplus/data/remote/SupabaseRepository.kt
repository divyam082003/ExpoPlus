package com.gd.expoplus.data.remote

import io.github.jan.supabase.postgrest.from

class SupabaseRepository {

    suspend fun insertTransaction(transaction: RemoteTransaction) {

        SupabaseClient.client
            .from("transactions")
            .insert(transaction)
    }

    suspend fun getTransactions(): List<RemoteTransaction> {

        return SupabaseClient.client
            .from("transactions")
            .select()
            .decodeList<RemoteTransaction>()
    }

    suspend fun deleteTransaction(id: String) {

        SupabaseClient.client
            .from("transactions")
            .delete {

                filter {
                    eq("id", id)
                }
            }
    }

    suspend fun deleteAllTransactions() {

        SupabaseClient.client
            .from("transactions")
            .delete {

                filter {
                    neq("id", "00000000-0000-0000-0000-000000000000")
                }
            }
    }
}