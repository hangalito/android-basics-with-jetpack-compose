package com.example.ipcalculator.data

data class Address(val address: List<Int>) {

    fun first() = address.first()

    fun second() = address[1]

    fun third() = address[2]

    fun fourth() = address[3]

    companion object {
        var newMask = "00000000.00000000.00000000.00000000"
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

            return binaryString
        }

        fun toDecimal(binaryOctet: String): String {
            val table = listOf(128, 64, 32, 16, 8, 4, 2, 1)
            var decimal = 0

            binaryOctet.forEachIndexed { index, char ->
                if (char == '1') decimal += table[index]
            }
            return decimal.toString()
        }

        fun getDefaultMask(borrowed: Int): String {
            return when (borrowed) {
                in 8..15 -> "255.0.0.0"
                in 16..23 -> "255.255.0.0"
                in 24..31 -> "255.255.255.0"
                else -> "0.0.0.0"
            }
        }

        fun getNewMaskBin(borrowed: Int): String {
            return if (borrowed == 0) "00000000.00000000.00000000.00000000"
            else {
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
                newMask = "${octets[0]}.${octets[1]}.${octets[2]}.${octets[3]}"
                "${octets[0]}.${octets[1]}.${octets[2]}.${octets[3]}"
            }
        }
    }

}