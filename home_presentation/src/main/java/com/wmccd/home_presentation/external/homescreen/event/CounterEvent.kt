package com.wmccd.home_presentation.external.homescreen.event

sealed interface CounterEvent{
    object OnBookCountTapped: CounterEvent
    object OnRecordCountTapped: CounterEvent
}