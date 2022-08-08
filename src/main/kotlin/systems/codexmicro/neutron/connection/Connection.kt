/*
 * @(#)Connection.kt
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

// import com.fazecast.jSerialComm.SerialPort
// import systems.codexmicro.neutron.connection.SerialConnection
// import systems.codexmicro.neutron.connection.ConnectionType

// package systems.codexmicro.neutron.connection

// class Connection(connectionType: ConnectionType, serialPort: SerialPort) {
//     private var connectionType: ConnectionType

//     init {
//         this.connectionType = connectionType

//         // TODO: Error handling

//         if (connectionType == ConnectionType.SERIAL) {
//             var serialConnection: SerialConnection = SerialConnection(serialPort.toString(), false)
//         } else if (connectionType == ConnectionType.ETHERNET) {
//             // var ethernetConnection: EthernetConnection = EthernetConnection(ethernetAddress.toString())
//         }
//     }
// }
