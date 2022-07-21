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
 * Updated: July 19, 2022
 */

package systems.codexmicro.neutron.util

enum class ReadTerminator {
    CR,
    LF,
    CR_LF,
    OPTCR_LF,
    LF_CR
}
