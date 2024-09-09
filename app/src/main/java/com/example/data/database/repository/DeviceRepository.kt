package com.example.data.database.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.domain.model.Device

@Dao
interface DeviceRepository {
    @Insert
    fun insert(entity: Device)

    @Insert
    fun insertAll(entity: List<Device>)

    @Update
    fun update(entity: Device)

    @Delete
    fun delete(entity: Device)

    @Query("SELECT * FROM Device")
    fun findAll(): List<Device>

    @Query("SELECT * FROM Device WHERE deviceID=:deviceId")
    fun findById(deviceId: Long): Device
}
