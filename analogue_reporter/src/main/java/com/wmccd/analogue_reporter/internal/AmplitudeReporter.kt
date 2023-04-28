package com.wmccd.analogue_reporter.internal

import com.wmccd.analogue_reporter.external.AnalogueAction


internal class AmplitudeReporter(): Reporter {

    private val amplitideObject = Object()

    fun setup(
        apiKey: String,
        someOtherKey: String
    ){
        //spin up the library object with all the properties needed
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