package com.example.domain.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.data.database.configuration.GeminiExampleDatabase
import com.example.data.database.repository.DeviceRepository
import com.example.domain.config.DeviceHelpers
import com.example.domain.config.DeviceResult
import com.example.domain.config.DeviceStatus
import com.example.domain.model.Device
import java.time.LocalDateTime
import java.util.Optional
import java.util.UUID

class SplashViewModel(
    private val context: Context,
    private val navController: NavController?) : ViewModel() {

    private val deviceRepository: DeviceRepository =
        GeminiExampleDatabase.getInstance(context).deviceRepository()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun getDeviceStatus(): Device {
        val result: DeviceResult = DeviceHelpers.getDevice(context)

        val optionalDefault: Optional<Device> =
            deviceRepository.findDefaultDevice(result.name, result.code)

        if (optionalDefault.isEmpty) {
            val deviceID = UUID.randomUUID().toString()

            val device = Device(
                deviceID = deviceID,
                isActive = true,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                name = result.name,
                code = result.code,
                status = DeviceStatus.APIKEY.toString(),
                passKeyID = null,
            )

            deviceRepository.insert(device)

            val optionalCreated: Optional<Device> = deviceRepository.findById(deviceID)

            if (optionalCreated.isEmpty) {
                throw Exception("Device not created")
            }

            return optionalCreated.get()

        } else {
            return optionalDefault.get()
        }
    }

    fun navigateToApiKey() {
        navController?.navigate("api-key")
    }
}
