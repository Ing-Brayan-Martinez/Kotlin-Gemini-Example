package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Device")
data class Device(
    @PrimaryKey(autoGenerate = false)
    val deviceID: String,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val name: String,
    val code: String,
    val status: String,
    val passKeyID: String? = null
)
