package com.wmccd.home_presentation.external.homescreen.state

sealed interface CounterState{
    object Loading: CounterState
    object Display: CounterState
    object Error: CounterState
}

data class CombinedCounterState(
    var state: CounterState,
    var loading: CounterStateWhenLoading,
    var displaying: CounterStateWhenDisplaying,
    var erroring: CounterStateWhenErroring
)