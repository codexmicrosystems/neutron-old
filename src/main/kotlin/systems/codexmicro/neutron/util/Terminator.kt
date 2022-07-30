/*
 * @(#)ReadTerminator.kt
 *
 * Copyright (C) 2022 Codex Microsystems All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 *
 * Author: Cody L. Wellman <cody@codexmicro.systems>
 *
 * Created: July 07, 2022
 * Updated: July 29, 2022
 */

package systems.codexmicro.neutron.util

enum class Terminator(value: Byte) {
    CR(13),
    LF(10);

    private var value: Byte

    init {
        this.value = value
    }

    fun toByte(): Byte {
        return value
    }
}
