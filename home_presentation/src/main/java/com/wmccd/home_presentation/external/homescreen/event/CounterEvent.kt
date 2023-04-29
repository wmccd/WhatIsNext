package com.wmccd.home_presentation.external.homescreen.event

sealed interface CounterEvent{
    object OnColorChangeButtonTapped: CounterEvent
}