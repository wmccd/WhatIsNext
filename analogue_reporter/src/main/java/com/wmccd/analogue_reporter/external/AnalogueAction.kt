package com.wmccd.analogue_reporter.external

import java.util.HashMap

sealed class AnalogueAction{
    data class Property(val key: String, val value: Any): AnalogueAction()
    data class Properties(val keyValues: HashMap<String, Any>): AnalogueAction()
    data class Event(val whatHappened: String, val keyValues: HashMap<String, Any> = hashMapOf() ): AnalogueAction()
    data class Exception(val whatWentWrong: String, val keyValues: HashMap<String, Any> = hashMapOf()): AnalogueAction()
    data class Trace(
        val tag: String, val whatHappened: String, val key: String = "", val value: Any = "", val moreKeyValues: HashMap<String, Any> = hashMapOf()
    ): AnalogueAction()
}
