package com.example.data.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

object BigDecimalConverter {
    @TypeConverter
    fun fromDouble(value: Double): BigDecimal {
        return BigDecimal(value)
    }

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): Double {
        return value.toDouble()
    }
}
