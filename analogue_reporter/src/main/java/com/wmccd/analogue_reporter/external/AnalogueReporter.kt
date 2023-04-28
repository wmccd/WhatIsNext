package com.wmccd.analogue_reporter.external

import com.wmccd.analogue_reporter.external.AnalogueAction

interface AnalogueReporter {
    fun setup(setupKeyValueMap:  HashMap<String, Any>, debugMode: Boolean)
    fun report(action: AnalogueAction)
}