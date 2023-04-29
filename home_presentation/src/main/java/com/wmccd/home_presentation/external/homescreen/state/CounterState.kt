package com.wmccd.home_presentation.external.homescreen.state

sealed interface CounterState{
    object Loading: CounterState
    object Displaying: CounterState
    object Erroring: CounterState
}
