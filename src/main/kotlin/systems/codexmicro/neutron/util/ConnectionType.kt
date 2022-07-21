/*
 * @(#)ConnectionType.kt
 *
 * Copyright (C) 2022 Codex Microsystems All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 *
 * Author: Cody L. Wellman <cody@codexmicro.systems>
 *
 * Created: July 21, 2022
 * Updated: July 21, 2022
 */

package systems.codexmicro.neutron.util

enum class ConnectionType(connectionType: String) {
    SERIAL("Serial"),
    ETHERNET("Ethernet");

    private var connectionType: String

    init {
        this.connectionType = connectionType
    }

    override fun toString(): String {
        return connectionType
    }
}
