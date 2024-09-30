package com.example.repository

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.BuildConfig.LOG_TAG
import com.example.data.database.configuration.GeminiExampleDatabase
import com.example.domain.model.Device
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime
import java.util.UUID


@RunWith(AndroidJUnit4::class)
class DeviceRepositoryTest {
    lateinit var geminiExampleDatabase: GeminiExampleDatabase

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation()
            .targetContext

        geminiExampleDatabase = Room.inMemoryDatabaseBuilder(
            appContext,
            GeminiExampleDatabase::class.java
        ).build();
    }

    @Test
    fun insertDevice() {
        val deviceRepository = geminiExampleDatabase.deviceRepository()
        val deviceID = UUID.randomUUID().toString()

        val device = Device(
            deviceID = deviceID,
            isActive = true,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            name = "Test Device",
            code = "TEST",
            status = "Active",
            passKeyID = "123456789",
        )

        deviceRepository.insert(device)

        val result: Device = deviceRepository.findById(deviceID).get()
        Log.d(LOG_TAG, result.toString())

        assertNotEquals(null, result.deviceID)
    }

    @Test
    fun findAllDevice() {
        val deviceRepository = geminiExampleDatabase.deviceRepository()

        val list: List<Device> = deviceRepository.findAll()

        Log.d(LOG_TAG, list.toString())

        assertNotEquals(null, list.first())
    }
}
