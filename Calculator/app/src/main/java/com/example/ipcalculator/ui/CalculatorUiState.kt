package com.example.ipcalculator.ui

import com.example.ipcalculator.data.Address


/**
 * Data class that represents the current UI state of the application
 */
data class CalculatorUiState(
    val ipAddress: Address,
    val defaultMask: Address,
    val newMask: Address,
    val currentBinaryIp: String = "",
    val currentDefaultMaksBin: String = "",
    val currentNewMaskBin: String = "",
    val expanded: Boolean = false
)