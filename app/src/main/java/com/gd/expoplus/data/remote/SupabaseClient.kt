package com.gd.expoplus.data.remote

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {

    val client = createSupabaseClient(
        supabaseUrl = "https://lldvcovulcblbqoxpznh.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxsZHZjb3Z1bGNibGJxb3hwem5oIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzkzNTMxNDksImV4cCI6MjA5NDkyOTE0OX0.NHPhcMfuDqL9BoH5bI0lJ6QG2L5lyMvyNOfyD2VAmAY"
    ) {

        install(Postgrest)
        install(Auth)
    }
}