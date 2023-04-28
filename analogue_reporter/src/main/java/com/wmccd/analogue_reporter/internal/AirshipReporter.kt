package com.wmccd.analogue_reporter.internal

import com.wmccd.analogue_reporter.external.AnalogueAction

internal class AirshipReporter : Reporter {

    private val amplitideObject = Object()

    fun setup(
        apiKey: String,
        someOtherKey: String

    ){
        //these are the api keys and settings required to get the destinations running
    }

    override fun report(analogueAction: AnalogueAction){
        when(analogueAction){
            is AnalogueAction.Event -> {}
            is AnalogueAction.Exception -> {}
            is AnalogueAction.Properties -> {}
            is AnalogueAction.Property -> {}
            is AnalogueAction.Trace -> {}
        }
    }
}