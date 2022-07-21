/*
 * @(#)BusAddress.kt
 *
 * Copyright (C) 2022 Codex Microsystems All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 *
 * Author: Cody L. Wellman <cody@codexmicro.systems>
 *
 * Created: July 06, 2022
 * Updated: July 21, 2022
 */

package systems.codexmicro.neutron

class BusAddress(busAddressPrimary: Byte, busAddressSecondary: Byte) {
    private lateinit var busAddressURL: String
    private var busAddressPrimary: Byte
    private var busAddressSecondary: Byte
    private val MIN_NON_ZERO: Byte = 0x60
    private val MAX_NON_ZERO: Byte = 0x7e

    init {
        if (busAddressPrimary < 0 || busAddressPrimary > 30) {
            throw IllegalArgumentException("ERROR: Invalid Primary Bus Address")
        } else {
            this.busAddressPrimary = busAddressPrimary
        }

        if (busAddressSecondary != 0.toByte() &&
                        (busAddressSecondary < MIN_NON_ZERO || busAddressSecondary > MAX_NON_ZERO)
        ) {
            throw IllegalArgumentException("ERROR: Invalid Secondary Bus Address")
        } else {
            this.busAddressSecondary = busAddressSecondary
        }
    }

    constructor(busAddressPrimary: Byte) : this(busAddressPrimary, 0) {}

    // TODO: fromBusAddressURL

    fun getBusAddressPrimary(): Byte {
        return this.busAddressPrimary
    }

    fun setBusAddressPrimary(busAddressPrimary: Byte) {
        this.busAddressPrimary = busAddressPrimary
    }

    fun getBusAddressSecondary(): Byte {
        return this.busAddressSecondary
    }

    fun setBusAddressSecondary(busAddressSecondary: Byte) {
        this.busAddressSecondary = busAddressSecondary
    }

    fun hasBusAddressSecondary(): Boolean {
        return this.busAddressSecondary != 0.toByte()
    }

    fun getBusAddressURL(): String {
        if (hasBusAddressSecondary()) {
            busAddressURL = "GPIB::" + this.busAddressPrimary + "," + this.busAddressSecondary
        } else {
            busAddressURL = "GPIB::" + this.busAddressPrimary
        }
        return busAddressURL
    }
}
