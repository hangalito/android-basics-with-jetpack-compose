package com.example.ipcalculator.data


/**
Data class [Address] to hold the IP and Mask information
 */
data class Address(val address: List<String>) {

    fun first() = address.first()

    fun second() = address[1]

    fun third() = address[2]

    fun fourth() = address[3]

    override fun toString(): String {
        return "${address.first()}.${address[1]}.${address[2]}.${address.last()}"
    }
}