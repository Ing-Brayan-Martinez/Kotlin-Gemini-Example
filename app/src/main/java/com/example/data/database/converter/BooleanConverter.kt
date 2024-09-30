package com.example.data.database.converter

import androidx.room.TypeConverter

object BooleanConverter {
    @TypeConverter
    fun fromString(value: Boolean): Char {
        return if (value) 'Y' else 'N'
    }

    @TypeConverter
    fun fromCharacter(value: Char): Boolean {
        return if (value == 'Y') java.lang.Boolean.TRUE else java.lang.Boolean.FALSE
    }
}
