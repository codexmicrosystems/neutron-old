/*
 * @(#)Serial.kt
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

package systems.codexmicro.neutron.util

enum class ParityType(value: Int) {
    NONE(0),
    ODD(1),
    EVEN(2),
    MARK(3),
    SPACE(4);

    private var value: Int

    init {
        this.value = value
    }

    fun toInt(): Int {
        return value
    }
}

enum class StopBits(value: Int) {
    ONE(10),
    ONE_HALF(15),
    TWO(20);

    private var value: Int

    init {
        this.value = value
    }

    fun toInt(): Int {
        return value
    }
}

enum class FlowControl(value: Int) {
    NONE(0),
    XON_XOFF(1),
    RTS_CTS(2),
    DTR_DSR(4);

    private var value: Int

    init {
        this.value = value
    }

    fun toInt(): Int {
        return value
    }
}