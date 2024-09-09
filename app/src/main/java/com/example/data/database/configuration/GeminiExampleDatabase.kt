package com.example.data.database.configuration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.database.repository.DeviceRepository
import com.example.domain.model.Device


@Database(
    entities = [
        Device::class
    ], version = 1
)
abstract class GeminiExampleDatabase private constructor() : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: GeminiExampleDatabase? = null

        fun getInstance(context: Context): GeminiExampleDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): GeminiExampleDatabase {
            val name = "gemini_example.db"
            return Room.databaseBuilder(context, GeminiExampleDatabase::class.java, name)
                .build()
        }
    }

    abstract fun deviceRepository(): DeviceRepository
}
