package com.wmccd.home_presentation.external.homescreen

import androidx.compose.runtime.State
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.CounterState
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenLoading

interface CounterViewModel {
    val currentState: State<CounterState>
    val uiLoadingState: State<CounterStateWhenLoading>
    val uiDisplayState: State<CounterStateWhenDisplaying>
    val uiErroringState: State<CounterStateWhenErroring>
    fun onEvent(notesEvent: CounterEvent)
}