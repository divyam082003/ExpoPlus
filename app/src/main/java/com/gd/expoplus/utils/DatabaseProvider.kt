package com.gd.expoplus.utils

import android.content.Context
import androidx.room.Room
import com.gd.expoplus.data.local.db.ExpoDatabase

object DatabaseProvider {

    @Volatile
    private var INSTANCE: ExpoDatabase? = null

    fun getDatabase(context: Context): ExpoDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                ExpoDatabase::class.java,
                "expo_database"
            ).fallbackToDestructiveMigration().build()

            INSTANCE = instance

            instance
        }
    }
}