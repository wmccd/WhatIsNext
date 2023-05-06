package com.wmccd.datasource_services.external.fakes

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter

class FakeAnalogueReporter: AnalogueReporter {
    override fun setup(setupKeyValueMap: HashMap<String, Any>, debugMode: Boolean) {}
    override fun report(action: AnalogueAction) {}
}