/*
 * @(#)HP5334Commands.kt
 *
 * Copyright (C) 2022 Codex Microsystems All rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 *
 * Author: Cody L. Wellman <cody@codexmicro.systems>
 *
 * Created: November 04, 2022
 * Updated: November 04, 2022
 */

package systems.codexmicro.neutron.instrument.util.hp5334commands

// Input command group
const val AC_A_CMD: String = "AA"
const val SLOPE_A_CMD: String = "AS"
const val TRIGGER_LEVEL_A_CMD: String = "AT"
const val AUTO_TRIG_CMD: String = "AU"
const val ATTEN_A_CMD: String = "AX"
const val IMPEDANCE_A_CMD: String = "AZ"
const val AC_B_CMD: String = "BA"
const val SLOPE_B_CMD: String = "BS"
const val TRIGGER_LEVEL_B_CMD: String = "BT"
const val ATTEN_B_CMD: String = "BX"
const val IMPEDANCE_B_CMD: String = "BZ"
const val COMMON_INPUTS_CMD: String = "CC"
const val FILTER_CMD: String = "FI"
const val SENSITIVITY_CMD: String = "SE"
const val REMOTE_TRIGGER_CMD: String = "TR"
const val START_ARM_CMD: String = "XA"
const val STOP_ARM_CMD: String = "XO"

// Function/data command group
const val FUNCTION_CMD: String = "FN"

// Gate command group
const val GATE_TIME_DELAY_CMD: String = "GA"
const val SINGLE_GATE_CYCLE_CMD: String = "GS"
const val GATE_AVERAGE_CMD: String = "GV"

// Math/memory command group
const val MATH_DISABLE_CMD: String = "MD"
const val NORMALIZE_CMD: String = "MN"
const val OFFSET_CMD: String = "MO"
const val RECALL_CMD: String = "MR"
const val STORE_CMD: String = "MS"

// Misc and special function command group
const val HIGH_SPEED_OUTPUT_CMD: String = "HS"
const val ID_CMD: String = "ID"
const val INITIALIZE_CMD: String = "IN"
const val RE_CMD: String = "RE"
const val SRQ_MASK_CMD: String = "SM"
const val TRANSMIT_CALIBRATION_CMD: String = "TC"
const val TRANSMIT_ERROR_CMD: String = "TE"
const val WAIT_TO_BE_ADDRESSED_CMD: String = "WA"
