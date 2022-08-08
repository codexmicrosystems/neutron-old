/*
 * @(#)EthernetConnection.kt
 *
 * Copyright (C) 2022 Codex Microsystems All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 *
 * Author: Cody L. Wellman <cody@codexmicro.systems>
 *
 * Created: August 06, 2022
 * Updated: August 06, 2022
 */

package systems.codexmicro.neutron.connection

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLConnection
import systems.codexmicro.neutron.util.Terminator

class EthernetConnection(ipAddress: URL, isPrologix: Boolean) {
    private var ipAddress: URL
    private lateinit var ipAddressConnection: URLConnection
    private lateinit var inputStream: BufferedReader
    private lateinit var outputStream: BufferedWriter

    init {
        try {
            this.ipAddress = ipAddress
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("ERROR: Invalid IP Address")
        }
        openConnection(ipAddress)
        defaultConfig()
        if (isPrologix == true) {
            prologixConfig()
        }
    }

    private fun defaultConfig() {}

    private fun prologixConfig() {}

    fun getIPAddress(): URL {
        return ipAddress
    }

    fun setIPAddress(ipAddress: URL) {
        this.ipAddress = ipAddress
    }

    fun openConnection(ipAddress: URL) {
        try {
            ipAddressConnection = ipAddress.openConnection()
            ipAddressConnection.setDoOutput(true)
            ipAddressConnection.connect()
            inputStream = BufferedReader(InputStreamReader(ipAddressConnection.getInputStream()))
            outputStream = BufferedWriter(OutputStreamWriter(ipAddressConnection.getOutputStream()))
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("ERROR: Could not Open Network Connection")
        }
    }

    fun closeConnection() {
        try {
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Close Network Connection")
        }
    }

    fun writeBytes(bytes: ByteArray) {
        try {
            ipAddressConnection.getOutputStream().write(bytes)
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Write Bytes")
        }
    }

    fun writeString(string: String) {
        try {
            ipAddressConnection.getOutputStream().write(string.toByteArray())
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Write String")
        }
    }

    fun terminateBytes(bytes: ByteArray, terminator: Terminator): ByteArray {
        return bytes + terminator.toByte()
    }

    fun terminateString(string: String, terminator: Terminator): ByteArray {
        return terminateBytes(string.toByteArray(), terminator)
    }

    // fun readBytes(bufferSize: Int): ByteArray {}

    fun readString() {}

    fun clearBuffer() {}
}
