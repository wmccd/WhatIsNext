package com.wmccd.home_presentation.external.fakes

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter

class FakeAnalogueReporter: AnalogueReporter {


    var lastInvokedList = arrayListOf<String>()

    override fun setup(setupKeyValueMap: HashMap<String, Any>, debugMode: Boolean) {}
    override fun report(action: AnalogueAction) {
        val invoked = when(action){
            is AnalogueAction.Trace -> action.whatHappened
            else -> "XXX"
        }
        lastInvokedList.add(invoked)
    }

}