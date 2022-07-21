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

import com.fazecast.jSerialComm.SerialPort
import systems.codexmicro.neutron.SerialConnection

package systems.codexmicro.neutron

class ConnectionType(connectionType: String, serialPort: SerialPort) {
    private var connectionType: String

    init {
        this.connectionType = connectionType

        // TODO: Error handling

        if (connectionType == "Serial") {
            var serialConnection: SerialConnection = SerialConnection(serialPort.toString())
        } else if (connectionType == "Ethernet") {
            // var ethernetConnection: EthernetConnection = EthernetConnection(ethernetAddress.toString())
        }
    }
}