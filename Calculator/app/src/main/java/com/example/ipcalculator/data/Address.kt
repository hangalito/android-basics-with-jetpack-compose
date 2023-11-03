package com.example.ipcalculator.data

import android.util.Log

data class Address(val address: List<Int>) {

    fun first() = address.first()

    fun second() = address[1]

    fun third() = address[2]

    fun fourth() = address[3]

    companion object {
        fun toBinary(decimalAddress: Int): String {
            if (decimalAddress == 0) return "00000000"

            var binaryString = ""
            var num = decimalAddress

            while (num > 0) {
                val reminder = num % 2
                binaryString = reminder.toString() + binaryString
                num /= 2
            }

            while (binaryString.length < 8) {
                binaryString = "0$binaryString"
            }

            Log.d("toBinary", binaryString)
            return binaryString
        }

        fun getDefaultMask(borrowed: Int): String {
            return when (borrowed) {
                in 8..16 -> "255.0.0.0"
                in 16..24 -> "255.255.0.0"
                in 25..32 -> "255.255.255.0"
                else -> "0.0.0.0"
            }
        }
    }

}