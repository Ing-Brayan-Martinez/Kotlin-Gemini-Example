package com.example.domain.config

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log


class DeviceResult(name: String, code: String)


object DeviceHelpers {

    fun getDevice(context: Context): DeviceResult {
        val deviceName =
            Settings.Global.getString(context.contentResolver, Settings.Global.DEVICE_NAME)
        val serialNumber = Settings.Secure.getString(context.contentResolver, "android_id")


        Log.d("DeviceInfo", "Device Name: $deviceName")
        Log.d("DeviceInfo", "Serial Number: $serialNumber")



        return DeviceResult(deviceName, serialNumber)
    }
}
