/*
 * @(#)PrologixEthernet.kt
 * 
 * Copyright (C) 2022 Codex Microsystems All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 * 
 * Author: Cody L. Wellman <cody@codexmicro.systems>
 * 
 * Created: July 06, 2022
 * Updated: August 04, 2022
 */

package systems.codexmicro.neutron

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.ByteArrayOutputStream
import java.net.URL
import java.net.URLConnection
import java.net.UnknownServiceException
import javax.script.ScriptException
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import java.nio.charset.Charset
import systems.codexmicro.neutron.util.Terminator

class PrologixEthernet(prologixURL: URL) {
    private var prologixURL: URL
    private lateinit var prologixURLConnection: URLConnection
    private lateinit var inputStream: BufferedReader
    private lateinit var outputStream: BufferedWriter

    init {
        try {
            this.prologixURL = prologixURL
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("ERROR: Invalid URL")
        }
        openConnection(prologixURL)
        defaultConfig()
    }

    private fun defaultConfig() {}

    fun getPrologixURL(): URL {
        return prologixURL
    }

    fun setPrologixURL(prologixURL: URL) {
        this.prologixURL = prologixURL
    }

    fun openConnection(prologixURL: URL) {
        try {
            prologixURLConnection = prologixURL.openConnection()
            prologixURLConnection.setDoOutput(true)
            prologixURLConnection.connect()
            inputStream = BufferedReader(InputStreamReader(prologixURLConnection.getInputStream()))
            outputStream =
                    BufferedWriter(OutputStreamWriter(prologixURLConnection.getOutputStream()))
        } catch (e: NullPointerException) {
            throw ScriptException("ERROR: Could not Open Network Connection")
        } catch (e: UnknownServiceException) {
            throw ScriptException("ERROR: Could not Open Network Connection")
        } catch (e: IOException) {
            throw ScriptException("ERROR: Could not Open Network Connection")
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

    private var readBytes: LinkedBlockingQueue<Byte> = LinkedBlockingQueue<Byte>()

    private fun prologixReadByte(timeoutMs: Long): Byte {
        var byteRead: Byte = this.readBytes.poll(timeoutMs, TimeUnit.MILLISECONDS)
        return byteRead
    }

    fun prologixClearReadBuffer() {
        var drainedBytes: List<Byte> = ArrayList<Byte>()
        this.readBytes.drainTo(drainedBytes.toMutableList())
        if (!drainedBytes.isEmpty()) {
            var bytes: ByteArray = ByteArray(drainedBytes.size.toInt())
            var i: Int = 0
            for (b: Byte in drainedBytes) {
                bytes[i++] = b
            }
        }
    }

    private fun prologixWriteRaw(bytes: ByteArray) {
        var prologixURLConnection: URLConnection = this.prologixURLConnection
        this.prologixURLConnection.getOutputStream().write(bytes)
    }

    private fun prologixWriteRaw(string: String) {
        prologixWriteRaw(string.toByteArray(Charset.forName("US_ASCII")))
    }

    private val LF_BYTE: Byte = 10
    private val CR_BYTE: Byte = 13
    private val ESC_BYTE: Byte = 27
    private val PLUS_BYTE: Byte = 43
    private val PROLOGIX_TERMINATOR: Byte = LF_BYTE

    private fun prologixCookString(bytes: ByteArray): ByteArray {
        var byteArrayOutput: ByteArrayOutputStream = ByteArrayOutputStream()
        for (b: Byte in bytes) {
            when (b) {
                LF_BYTE ->
                CR_BYTE ->
                ESC_BYTE ->
                PLUS_BYTE -> byteArrayOutput.write(ESC_BYTE.toInt())
                else -> {
                    byteArrayOutput.write(b.toInt())
                }
            }
        }
        byteArrayOutput.write(byteArrayOutput.toByteArray())
        return byteArrayOutput.toByteArray()
    }

    // TODO: Write Cooked Error Handling
    fun prologixWriteCooked(bytes: ByteArray) {
        prologixWriteRaw(prologixCookString(bytes))
    }

    fun prologixWriteCooked(string: String) {
        prologixWriteRaw(string)
    }
}
