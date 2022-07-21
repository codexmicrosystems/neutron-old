/*
 * @(#)Instrument.kt
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

package systems.codexmicro.neutron.instrument

import systems.codexmicro.neutron.BusAddress

class Instrument(instrumentName: String, busAddress: BusAddress) {
    private var instrumentName: String
    private var busAddress: BusAddress
    private var poweredOn: Boolean = true

    init {
        this.instrumentName = instrumentName
        this.busAddress = busAddress
    }

    fun getInstrumentName(): String {
        return instrumentName
    }

    fun setInstrumentName(instrumentName: String) {
        this.instrumentName = instrumentName
    }

    fun getBusAddress(): BusAddress {
        return busAddress
    }

    fun setBusAddress(busAddress: BusAddress) {
        this.busAddress = busAddress
    }

    fun getPoweredOn() {
        // TODO:
    }

    fun setPoweredOn() {
        // TODO:
    }
}
