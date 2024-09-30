package com.example.domain.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.data.database.configuration.GeminiExampleDatabase
import com.example.data.database.repository.DeviceRepository
import com.example.domain.config.DeviceHelpers
import com.example.domain.config.DeviceResult

class SplashViewModel(
    private val context: Context,
    private val navController: NavController?) : ViewModel() {

    private val database = GeminiExampleDatabase.getInstance(context)

    suspend fun getDeviceStatus() {
        val deviceRepository: DeviceRepository = database.deviceRepository()

        val result: DeviceResult = DeviceHelpers.getDevice(context)


    }

}
