package com.wmccd.record_repository.external.fakes

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter

class FakeAnalogueReporter: AnalogueReporter {

    var lastInvoked = ""

    override fun setup(setupKeyValueMap: HashMap<String, Any>, debugMode: Boolean) {}
    override fun report(action: AnalogueAction) {

        lastInvoked = when(action){
            is AnalogueAction.Trace -> action.whatHappened
            else -> "XXX"
        }    }

}