package com.example.data.database.configuration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.converter.BigDecimalConverter
import com.example.data.database.converter.BooleanConverter
import com.example.data.database.converter.LocalDateConverter
import com.example.data.database.converter.LocalDateTimeConverter
import com.example.data.database.repository.DeviceRepository
import com.example.domain.model.Device


@Database(
    entities = [
        Device::class
    ], version = 1
)
@TypeConverters(
    value = [
        BigDecimalConverter::class,
        BooleanConverter::class,
        LocalDateConverter::class,
        LocalDateTimeConverter::class
    ]
)
abstract class GeminiExampleDatabase : RoomDatabase() {
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
