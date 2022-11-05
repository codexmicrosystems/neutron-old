/*
 * @(#)HP5334.kt
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

package systems.codexmicro.neutron.instrument.frequencycounter

import systems.codexmicro.neutron.connection.SerialConnection
import systems.codexmicro.neutron.instrument.util.hp5334commands.*
import systems.codexmicro.neutron.util.Terminator
import systems.codexmicro.neutron.util.prologixcommands.*

class HP5334(serialConnection: SerialConnection, busAddress: String) {
    private var serialConnection: SerialConnection
    private var busAddress: String

    init {
        this.serialConnection = serialConnection
        this.busAddress = busAddress
    }

    // Input command group
    fun setInputCoupling(instrumentInput: String, couplingType: String) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (instrumentInput == "A" && couplingType == "DC") {
            serialConnection.sendCommand(AC_A_CMD + "0", Terminator.LF)
        } else if (instrumentInput == "A" && couplingType == "AC") {
            serialConnection.sendCommand(AC_A_CMD + "1", Terminator.LF)
        } else if (instrumentInput == "B" && couplingType == "DC") {
            serialConnection.sendCommand(AC_B_CMD + "0", Terminator.LF)
        } else if (instrumentInput == "B" && couplingType == "AC") {
            serialConnection.sendCommand(AC_B_CMD + "1", Terminator.LF)
        } else {
            println("[ERROR] Instrument Input or Coupling Type Invalid")
        }
    }

    fun setInputSlope(instrumentInput: String, slopePolarity: String) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (instrumentInput == "A" && slopePolarity == "POSITIVE") {
            serialConnection.sendCommand(SLOPE_A_CMD + "0", Terminator.LF)
        } else if (instrumentInput == "A" && slopePolarity == "NEGATIVE") {
            serialConnection.sendCommand(SLOPE_A_CMD + "1", Terminator.LF)
        } else if (instrumentInput == "B" && slopePolarity == "POSITIVE") {
            serialConnection.sendCommand(SLOPE_B_CMD + "0", Terminator.LF)
        } else if (instrumentInput == "B" && slopePolarity == "NEGATIVE") {
            serialConnection.sendCommand(SLOPE_B_CMD + "1", Terminator.LF)
        } else {
            println("[ERROR] Instrument Input or Slope Polarity Invalid")
        }
    }

    fun setTriggerLevel(instrumentInput: String, triggerLevel: Double) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (instrumentInput == "A") {
            serialConnection.sendCommand(TRIGGER_LEVEL_A_CMD + triggerLevel, Terminator.LF)
        } else if (instrumentInput == "B") {
            serialConnection.sendCommand(TRIGGER_LEVEL_B_CMD + triggerLevel, Terminator.LF)
        } else {
            println("[ERROR] Instrument Input or Trigger Level Invalid")
        }
    }

    fun setAutoTrigger(triggerState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (triggerState == true) {
            serialConnection.sendCommand(AUTO_TRIG_CMD + "1", Terminator.LF)
        } else if (triggerState == false) {
            serialConnection.sendCommand(AUTO_TRIG_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] Trigger State Invalid")
        }
    }

    fun setInputAttenuation(instrumentInput: String, attenuationState: String) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (instrumentInput == "A" && attenuationState == "X1") {
            serialConnection.sendCommand(ATTEN_A_CMD + "0", Terminator.LF)
        } else if (instrumentInput == "A" && attenuationState == "X1") {
            serialConnection.sendCommand(ATTEN_A_CMD + "1", Terminator.LF)
        } else if (instrumentInput == "B" && attenuationState == "X1") {
            serialConnection.sendCommand(ATTEN_B_CMD + "0", Terminator.LF)
        } else if (instrumentInput == "B" && attenuationState == "X1") {
            serialConnection.sendCommand(ATTEN_B_CMD + "1", Terminator.LF)
        } else {
            println("[ERROR] Instrument Input or Attenuation State Invalid")
        }
    }

    fun setInputImpedance(instrumentInput: String, inputImpedance: String) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (instrumentInput == "A" && inputImpedance == "1M") {
            serialConnection.sendCommand(IMPEDANCE_A_CMD + "0", Terminator.LF)
        } else if (instrumentInput == "A" && inputImpedance == "50") {
            serialConnection.sendCommand(IMPEDANCE_A_CMD + "1", Terminator.LF)
        } else if (instrumentInput == "B" && inputImpedance == "1M") {
            serialConnection.sendCommand(IMPEDANCE_B_CMD + "0", Terminator.LF)
        } else if (instrumentInput == "B" && inputImpedance == "50") {
            serialConnection.sendCommand(IMPEDANCE_B_CMD + "1", Terminator.LF)
        } else {
            println("[ERROR] Instrument Input or Input Impedance Invalid")
        }
    }

    fun setCommonInputs(commonInputsState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (commonInputsState == true) {
            serialConnection.sendCommand(COMMON_INPUTS_CMD + "1", Terminator.LF)
        } else if (commonInputsState == false) {
            serialConnection.sendCommand(COMMON_INPUTS_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] Common Inputs State Invalid")
        }
    }

    fun setInputFilter(inputFilterState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (inputFilterState == true) {
            serialConnection.sendCommand(FILTER_CMD + "1", Terminator.LF)
        } else if (inputFilterState == false) {
            serialConnection.sendCommand(FILTER_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] Input Filter State Invalid")
        }
    }

    fun setSensitivity(sensitivityState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (sensitivityState == true) {
            serialConnection.sendCommand(SENSITIVITY_CMD + "1", Terminator.LF)
        } else if (sensitivityState == false) {
            serialConnection.sendCommand(SENSITIVITY_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] Sensitivity State Invalid")
        }
    }

    fun setRemoteTrigger(remoteTriggerState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (remoteTriggerState == true) {
            serialConnection.sendCommand(REMOTE_TRIGGER_CMD + "1", Terminator.LF)
        } else if (remoteTriggerState == false) {
            serialConnection.sendCommand(REMOTE_TRIGGER_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] Remote Trigger State Invalid")
        }
    }

    fun setExternalArm(externalArmState: String, externalArmPolarity: String) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (externalArmState == "START" && externalArmPolarity == "POSITIVE") {
            serialConnection.sendCommand(START_ARM_CMD + "1", Terminator.LF)
        } else if (externalArmState == "START" && externalArmPolarity == "OFF") {
            serialConnection.sendCommand(START_ARM_CMD + "2", Terminator.LF)
        } else if (externalArmState == "START" && externalArmPolarity == "NEGATIVE") {
            serialConnection.sendCommand(START_ARM_CMD + "3", Terminator.LF)
        } else if (externalArmState == "STOP" && externalArmPolarity == "POSITIVE") {
            serialConnection.sendCommand(STOP_ARM_CMD + "1", Terminator.LF)
        } else if (externalArmState == "STOP" && externalArmPolarity == "OFF") {
            serialConnection.sendCommand(STOP_ARM_CMD + "2", Terminator.LF)
        } else if (externalArmState == "STOP" && externalArmPolarity == "NEGATIVE") {
            serialConnection.sendCommand(STOP_ARM_CMD + "3", Terminator.LF)
        } else {
            println("[ERROR] External Arm State or External Arm Polarity Invalid")
        }
    }

    // Function/data command group
    fun getFrequency(instrumentInput: String, readTimes: Int): String {
        lateinit var returnFrequency: String
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (instrumentInput == "A") {
            serialConnection.sendCommand(FUNCTION_CMD + "1", Terminator.LF)
            returnFrequency = serialConnection.readString(readTimes)
        } else if (instrumentInput == "B") {
            serialConnection.sendCommand(FUNCTION_CMD + "2", Terminator.LF)
            returnFrequency = serialConnection.readString(readTimes)
        } else if (instrumentInput == "C") {
            serialConnection.sendCommand(FUNCTION_CMD + "3", Terminator.LF)
            returnFrequency = serialConnection.readString(readTimes)
        } else {
            println("[ERROR] Instrument Input Invalid")
        }
        return returnFrequency
    }

    fun getPeriod(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "4", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getTimeIntervalAB(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "5", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getTimeIntervalABDelay(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "6", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getRatioAB(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "7", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getTotalizeStop(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "8", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getTotalizeStart(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "9", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getPulseWidthA(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "10", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getRiseFallTimeA(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "11", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getDVM(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "12", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getTriggerLevels(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(FUNCTION_CMD + "13", Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun getChannelPeaks(instrumentInput: String, readTimes: Int): String {
        lateinit var returnChannelPeaks: String
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (instrumentInput == "A") {
            serialConnection.sendCommand(FUNCTION_CMD + "14", Terminator.LF)
            returnChannelPeaks = serialConnection.readString(readTimes)
        } else if (instrumentInput == "B") {
            serialConnection.sendCommand(FUNCTION_CMD + "15", Terminator.LF)
            returnChannelPeaks = serialConnection.readString(readTimes)
        } else {
            println("[ERROR] Instrument Input Invalid")
        }
        return returnChannelPeaks
    }

    // Gate command group
    fun setGateTimeDelay(gateTime: Double) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(GATE_TIME_DELAY_CMD + gateTime, Terminator.LF)
    }

    fun setSingleGateCycle(singleGateCycleState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (singleGateCycleState == true) {
            serialConnection.sendCommand(SINGLE_GATE_CYCLE_CMD + "1", Terminator.LF)
        } else if (singleGateCycleState == false) {
            serialConnection.sendCommand(SINGLE_GATE_CYCLE_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] Single Gate Cycle State Invalid")
        }
    }

    fun setGateAverage(gateAverageState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (gateAverageState == true) {
            serialConnection.sendCommand(GATE_AVERAGE_CMD + "1", Terminator.LF)
        } else if (gateAverageState == false) {
            serialConnection.sendCommand(GATE_AVERAGE_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] Gate Average State Invalid")
        }
    }

    // Math/memory command group
    fun disableMath(mathDisableState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (mathDisableState == true) {
            serialConnection.sendCommand(MATH_DISABLE_CMD + "1", Terminator.LF)
        } else if (mathDisableState == false) {
            serialConnection.sendCommand(MATH_DISABLE_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] Math Disable State Invalid")
        }
    }

    fun setNormalize(normalizeValue: Double) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(NORMALIZE_CMD + normalizeValue, Terminator.LF)
    }

    fun setOffset(offsetValue: Double) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(OFFSET_CMD + offsetValue, Terminator.LF)
    }

    fun recallSetup(setupRegister: Int) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(RECALL_CMD + setupRegister, Terminator.LF)
    }

    fun storeSetup(setupRegister: Int) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(STORE_CMD + setupRegister, Terminator.LF)
    }

    // Misc and special function command group
    fun setHighSpeedOutput(highSpeedOutputState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (highSpeedOutputState == true) {
            serialConnection.sendCommand(HIGH_SPEED_OUTPUT_CMD + "1", Terminator.LF)
        } else if (highSpeedOutputState == false) {
            serialConnection.sendCommand(HIGH_SPEED_OUTPUT_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] High Speed Output State Invalid")
        }
    }

    fun getDeviceID(readTimes: Int): String {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(ID_CMD, Terminator.LF)
        return serialConnection.readString(readTimes)
    }

    fun initalize() {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(INITIALIZE_CMD, Terminator.LF)
    }

    fun reset() {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(RE_CMD, Terminator.LF)
    }

    fun setSRQMask(srqMask: Int) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(SRQ_MASK_CMD + srqMask, Terminator.LF)
    }

    fun transmitCalibrationData() {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(TRANSMIT_CALIBRATION_CMD, Terminator.LF)
    }

    fun transmitError() {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        serialConnection.sendCommand(TRANSMIT_ERROR_CMD, Terminator.LF)
    }

    fun setWaitToBeAddressed(waitToBeAddressedState: Boolean) {
        serialConnection.sendCommand(ADDR_CMD + busAddress, Terminator.LF)
        if (waitToBeAddressedState == true) {
            serialConnection.sendCommand(WAIT_TO_BE_ADDRESSED_CMD + "1", Terminator.LF)
        } else if (waitToBeAddressedState == false) {
            serialConnection.sendCommand(WAIT_TO_BE_ADDRESSED_CMD + "0", Terminator.LF)
        } else {
            println("[ERROR] Wait To Be Addressed State Invalid")
        }
    }
}
