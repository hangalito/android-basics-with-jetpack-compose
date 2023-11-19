package com.example.ipcalculator.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ipcalculator.data.Address
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel : ViewModel() {

    /**
     * Calculator state for this session
     */
    private val _uiState = MutableStateFlow(
        CalculatorUiState(
            ipAddress = Address(listOf("0", "0", "0", "0")),
            defaultMask = Address(listOf("0", "0", "0", "0")),
            newMask = Address(listOf("0", "0", "0", "0"))
        )
    )
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()


    var usedBits by mutableStateOf("0")
        private set

    var octet1 by mutableStateOf("0")
        private set
    var octet2 by mutableStateOf("0")
        private set
    var octet3 by mutableStateOf("0")
        private set
    var octet4 by mutableStateOf("0")
        private set


    fun updateBorrowedBits(borrowedValue: String) {
        usedBits = try {
            if (borrowedValue.toInt() < 32) {
                borrowedValue.dropWhile { char -> char == '0' }
            } else {
                "31"
            }
        } catch (_: Exception) {
            borrowedValue.dropWhile { char -> char == '0' }
        }
        if (usedBits.isEmpty()) {
            usedBits = "0"
        }
        updateBinaryValues()

        var defaultMaskBin = ""
        val newMask = getNewMaskBin(usedBits.toInt())
        val defaultMask = Address(getDefaultMaks(usedBits.toInt()).split("."))
        var newMaskDec = ""

        defaultMask.address.forEachIndexed { i, s ->
            defaultMaskBin += if (i == _uiState.value.defaultMask.address.size) {
                toBinary(s.toInt())
            } else {
                "${toBinary(s.toInt())}."
            }
        }
        newMask.split(".").forEachIndexed { i, s ->
            newMaskDec += if (i == newMask.split(".").size - 1) {
                toDecimal(s)
            } else {
                "${toDecimal(s)}."
            }
        }

        _uiState.update { currentState ->
            currentState.copy(
                defaultMask = defaultMask,
                currentDefaultMaksBin = defaultMaskBin,
                currentNewMaskBin = newMask,
                newMask = Address(newMaskDec.split("."))
            )
        }
    }

    fun updateOctet1(octetValue: String) {
        try {
            octet1 = if (octetValue.toInt() < 256) {
                octetValue.dropWhile { char -> char == '0' }
            } else {
                "255"
            }
        } catch (_: Exception) {
            octet1 = octetValue.dropWhile { char -> char == '0' }
            if (octet1.isEmpty()) {
                octet1 = "0"
            }
        }

        val currentAddress = mutableListOf<String>()
        _uiState.value.ipAddress.let {
            currentAddress.add(octet1)
            currentAddress.add(it.second())
            currentAddress.add(it.third())
            currentAddress.add(it.fourth())
        }

        _uiState.update { currentState ->
            currentState.copy(ipAddress = Address(currentAddress))
        }
        updateBinaryValues()
    }

    fun updateOctet2(octetValue: String) {
        try {
            octet2 = if (octetValue.toInt() < 256) {
                octetValue.dropWhile { char -> char == '0' }
            } else {
                "255"
            }
        } catch (_: Exception) {
            octet2 = octetValue.dropWhile { char -> char == '0' }
            if (octet2.isEmpty()) {
                octet2 = "0"
            }
        }

        val currentAddress = mutableListOf<String>()
        _uiState.value.ipAddress.let {
            currentAddress.add(it.first())
            currentAddress.add(octet2)
            currentAddress.add(it.third())
            currentAddress.add(it.fourth())
        }

        _uiState.update { currentState ->
            currentState.copy(ipAddress = Address(currentAddress))
        }
        updateBinaryValues()
    }

    fun updateOctet3(octetValue: String) {
        try {
            octet3 = if (octetValue.toInt() < 256) {
                octetValue.dropWhile { char -> char == '0' }
            } else {
                "255"
            }
        } catch (_: Exception) {
            octet3 = octetValue.dropWhile { char -> char == '0' }
            if (octet3.isEmpty()) {
                octet3 = "0"
            }
        }

        val currentAddress = mutableListOf<String>()
        _uiState.value.ipAddress.let {
            currentAddress.add(it.first())
            currentAddress.add(it.second())
            currentAddress.add(octet3)
            currentAddress.add(it.fourth())
        }

        _uiState.update { currentState ->
            currentState.copy(ipAddress = Address(currentAddress))
        }
        updateBinaryValues()
    }

    fun updateOctet4(octetValue: String) {
        try {
            octet4 = if (octetValue.toInt() < 256) {
                octetValue.dropWhile { char -> char == '0' }
            } else {
                "255"
            }
        } catch (_: Exception) {
            octet4 = octetValue.dropWhile { char -> char == '0' }
            if (octet4.isEmpty()) {
                octet4 = "0"
            }
        }

        val currentAddress = mutableListOf<String>()
        _uiState.value.ipAddress.let {
            currentAddress.add(it.first())
            currentAddress.add(it.second())
            currentAddress.add(it.third())
            currentAddress.add(octet4)
        }

        _uiState.update { currentState ->
            currentState.copy(ipAddress = Address(currentAddress))
        }
        updateBinaryValues()
    }

    private fun updateBinaryValues() {
        var ip = ""
        var defaultMask = ""
        val newMask = getNewMaskBin(borrowed = usedBits.toInt())

        _uiState.value.ipAddress.address.forEachIndexed { index, value ->
            ip += if (index == _uiState.value.ipAddress.address.size - 1) {
                toBinary(value.toInt())
            } else {
                "${toBinary(value.toInt())}."
            }
        }
        _uiState.value.defaultMask.address.forEachIndexed { index, char ->
            defaultMask += if (index == _uiState.value.defaultMask.address.size - 1) {
                toBinary(char.toInt())
            } else {
                "${toBinary(char.toInt())}."
            }
        }

        _uiState.update { currentState ->
            currentState.copy(
                currentBinaryIp = ip,
                currentDefaultMaksBin = defaultMask,
                currentNewMaskBin = newMask
            )
        }
    }

    private fun toBinary(decimal: Int): String {
        /**
         * Function to convert a decimal value [toBinary]
         */

        if (decimal == 0) return "00000000"

        var binaryString = ""
        var num = decimal

        while (num > 0) {
            val reminder = num % 2
            binaryString = reminder.toString() + binaryString
            num /= 2
        }

        while (binaryString.length < 8) {
            binaryString = "0$binaryString"
        }

        return binaryString
    }

    private fun toDecimal(binary: String): String {
        /**
         * Function to convert a decimal value [toDecimal]
         */
        val table = listOf(128, 64, 32, 16, 8, 4, 2, 1)
        var decimal = 0

        binary.forEachIndexed { index, char ->
            if (char == '1') {
                decimal += table[index]
            }
        }

        return decimal.toString()
    }

    private fun getDefaultMaks(borrowed: Int): String {
        /**
         * Function to [getDefaultMaks] according
         * to the current address
         */

        return when (borrowed) {
            in 8 until 16 -> "255.0.0.0"
            in 16 until 24 -> "255.255.0.0"
            in 24 until 32 -> "255.255.255.0"
            else -> "0.0.0.0"
        }
    }

    private fun getNewMaskBin(borrowed: Int): String {
        return if (borrowed == 0) {
            "00000000.00000000.00000000.00000000"
        } else {
            var new = ""
            val octets = mutableListOf("", "", "", "")

            repeat(borrowed) { new += "1" }
            repeat(32 - borrowed) { new += "0" }

            for (i in new.indices) {
                when (i) {
                    in 0..7 -> octets[0] += new.toList()[i].toString()
                    in 8..15 -> octets[1] += new.toList()[i].toString()
                    in 16..23 -> octets[2] += new.toList()[i].toString()
                    else -> octets[3] += new.toList()[i].toString()
                }
            }
            "${octets[0]}.${octets[1]}.${octets[2]}.${octets[3]}"
        }
    }

    fun expand() {
        _uiState.update { currentState ->
            currentState.copy(expanded = !uiState.value.expanded)
        }
    }

}
