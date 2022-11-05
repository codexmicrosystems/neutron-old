/*
 * @(#)SerialConnection.kt
 *
 * Copyright (C) 2022 Codex Microsystems All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 *
 * Author: Cody L. Wellman <cody@codexmicro.systems>
 *
 * Created: July 20, 2022
 * Updated: November 03, 2022
 */

package systems.codexmicro.neutron.connection

import com.fazecast.jSerialComm.SerialPort
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import systems.codexmicro.neutron.util.FlowControl
import systems.codexmicro.neutron.util.ParityType
import systems.codexmicro.neutron.util.StopBits
import systems.codexmicro.neutron.util.Terminator

class SerialConnection(serialPort: String) {
    private var commPort: SerialPort
    private lateinit var inputStream: BufferedReader
    private lateinit var outputStream: BufferedWriter
    private var timeoutMode: Int = SerialPort.TIMEOUT_READ_SEMI_BLOCKING
    private var readTimeout: Int = 0
    private var writeTimeout: Int = 0
    private var baudRate: Int = 115200
    private var dataBits: Int = 8
    private var parityType: ParityType = ParityType.NONE
    private var stopBits: StopBits = StopBits.ONE
    private var flowControl: FlowControl = FlowControl.NONE

    init {
        try {
            commPort = SerialPort.getCommPort(serialPort)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("ERROR: Invalid Serial Port")
        }
        openConnection(commPort)
        commPort.setComPortTimeouts(timeoutMode, readTimeout, writeTimeout)
        commPort.setComPortParameters(baudRate, dataBits, stopBits.toInt(), parityType.toInt())
        commPort.setFlowControl(flowControl.toInt())
    }

    fun setSerialParams(
            baudRate: Int,
            dataBits: Int,
            parityType: ParityType,
            stopBits: StopBits,
            flowControl: FlowControl
    ) {
        this.baudRate = baudRate
        this.dataBits = dataBits
        this.parityType = parityType
        this.stopBits = stopBits
        this.flowControl = flowControl
    }

    fun setSerialTimeouts(timeoutMode: Int, readTimeout: Int, writeTimeout: Int) {
        this.timeoutMode = timeoutMode
        this.readTimeout = readTimeout
        this.writeTimeout = writeTimeout
    }

    fun getSerialPort(): SerialPort {
        return commPort
    }

    fun setSerialPort(serialPort: SerialPort) {
        this.commPort = serialPort
    }

    // TODO: Move serialPort.openPort() and .closePort() into try-catch
    // Change commPort.getOutputStream() and commPort.getInputStream()
    // to inputStream and outputStream variables

    fun openConnection(serialPort: SerialPort) {
        try {
            inputStream = BufferedReader(InputStreamReader(serialPort.getInputStream()))
            outputStream = BufferedWriter(OutputStreamWriter(serialPort.getOutputStream()))
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Open Serial Connection")
        }
        serialPort.openPort()
    }

    fun closeConnection(serialPort: SerialPort) {
        try {
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Close Serial Connection")
        }
        serialPort.closePort()
    }

    fun writeBytes(bytes: ByteArray) {
        try {
            commPort.getOutputStream().write(bytes)
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Write Bytes")
        }
    }

    fun writeString(string: String) {
        try {
            commPort.getOutputStream().write(string.toByteArray())
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

    fun sendCommand(bytes: ByteArray, terminator: Terminator) {
        try {
            commPort.getOutputStream().write(bytes + terminator.toByte())
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Send Command")
        }
    }

    fun sendCommand(string: String, terminator: Terminator) {
        try {
            commPort.getOutputStream().write(string.toByteArray() + terminator.toByte())
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Send Command")
        }
    }

    fun readBytes(): Int {
        try {
            return commPort.getInputStream().read()
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Read Bytes")
        }
    }

    // TODO: Write detection of terminator and allow reading n times.
    // Counter that counts n times of reading CR & LF and then
    // breaks the loop?

    fun readString(): String {
        try {
            return Character.toString(readBytes().toChar())
        } catch (e: IOException) {
            throw IOException("ERROR: Could not Read String")
        }
    }
}
