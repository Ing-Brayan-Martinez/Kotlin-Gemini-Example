package com.example.data.database.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.domain.model.Device
import java.util.Optional

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

    @Query("SELECT * FROM Device WHERE isActive=1")
    fun findAll(): List<Device>

    @Query("SELECT * FROM Device WHERE deviceID=:deviceId AND isActive=1")
    fun findById(deviceId: String): Optional<Device>

    @Query("SELECT * FROM Device WHERE name=:name AND code=:code AND isActive=1")
    fun findDefaultDevice(name: String, code: String): Optional<Device>
}
