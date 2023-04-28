package com.wmccd.analogue_reporter.internal

import com.wmccd.analogue_reporter.external.AnalogueAction


interface Reporter{
    fun report(analogueAction: AnalogueAction)
}