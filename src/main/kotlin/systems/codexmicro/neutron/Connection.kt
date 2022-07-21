/*
 * @(#)Connection.kt
 *
 * Copyright (C) 2022 Codex Microsystems All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 *
 * Author: Cody L. Wellman <cody@codexmicro.systems>
 *
 * Created: July 20, 2022
 * Updated: July 20, 2022
 */

package systems.codexmicro.neutron

interface Connection {
    fun defaultConfig() {}

    fun openConnection() {}

    fun closeConnection() {}

    fun writeBytes() {}

    fun writeString() {}

    fun readBytes() {}

    fun readString() {}

    fun clearBuffer() {}
}
