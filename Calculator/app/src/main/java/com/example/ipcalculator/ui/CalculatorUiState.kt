package com.example.ipcalculator.ui

import com.example.ipcalculator.data.Address


/**
 * Data class that represents the current UI state of the application
 */
data class CalculatorUiState(
    val ipAddress: Address = Address(listOf("0", "0", "0", "0")),
    val defaultMask: Address = Address(listOf("0", "0", "0", "0")),
    val newMask: Address = Address(listOf("0", "0", "0", "0")),
    val currentBinaryIp: String = "0000.0000.0000.0000",
    val currentDefaultMaksBin: String = "0000.0000.0000.0000",
    val currentNewMaskBin: String = "0000.0000.0000.0000",
    val expanded: Boolean = false
)