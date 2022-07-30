/*
 * @(#)Type.kt
 *
 * Copyright (C) 2022 Codex Microsystems All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 *
 * Author: Cody L. Wellman <cody@codexmicro.systems>
 *
 * Created: July 29, 2022
 * Updated: July 29, 2022
 */

package systems.codexmicro.neutron.address

enum class AddressType() {
    GPIB,
    USB,
    IP,
    SERIAL,
    UNKNOWN
}
