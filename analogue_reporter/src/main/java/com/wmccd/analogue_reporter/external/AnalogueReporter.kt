package com.wmccd.analogue_reporter.external

interface AnalogueReporter {
    fun setup(setupKeyValueMap:  HashMap<String, Any>, debugMode: Boolean)
    fun report(action: AnalogueAction)
}