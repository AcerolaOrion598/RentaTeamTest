package com.example.rentateamtest.utils

import java.io.IOException

class Utils {

    companion object {
        @Throws(InterruptedException::class, IOException::class)
        fun isServerAvailable(): Boolean {
            val command = "ping -c 1 reqres.in"
            return Runtime.getRuntime().exec(command).waitFor() == 0
        }
    }
}