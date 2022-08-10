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
 * Updated: August 10, 2022
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

    init {
        try {
            commPort = SerialPort.getCommPort(serialPort)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("ERROR: Invalid Serial Port")
        }
        openConnection(commPort)
        defaultConfig(commPort, 115200, 8, ParityType.NONE, StopBits.ONE, FlowControl.NONE)
    }

    private fun defaultConfig(
            serialPort: SerialPort,
            baudRate: Int,
            dataBits: Int,
            parityType: ParityType,
            stopBits: StopBits,
            flowControl: FlowControl
    ) {
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0)
        serialPort.setComPortParameters(baudRate, dataBits, stopBits.toInt(), parityType.toInt())
        serialPort.setFlowControl(flowControl.toInt())
    }

    fun getSerialPort(): SerialPort {
        return commPort
    }

    fun setSerialPort(serialPort: SerialPort) {
        this.commPort = serialPort
    }

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

    // TODO: Figure out how to make this shit work.

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

    // TODO: Figgure out how to read from the serial.

    fun readBytes(): ByteArray {
        return commPort.getInputStream().readBytes()
    }

    fun readString(): String {
        return readBytes().toString()
    }

    fun clearBuffer() {}
}
