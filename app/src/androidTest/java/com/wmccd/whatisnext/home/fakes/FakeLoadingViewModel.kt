package com.wmccd.whatisnext.home.fakes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.wmccd.home_presentation.external.homescreen.CounterViewModel
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.CounterState
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenLoading

class FakeLoadingCounterViewModel(): CounterViewModel {
    override val currentState: State<CounterState> = mutableStateOf(CounterState.Loading)
    override val uiLoadingState: State<CounterStateWhenLoading> = mutableStateOf(
        CounterStateWhenLoading(
            message = "LOADING"
        )
    )
    override val uiDisplayState: State<CounterStateWhenDisplaying> = mutableStateOf(
        CounterStateWhenDisplaying()
    )
    override val uiErroringState: State<CounterStateWhenErroring> = mutableStateOf(
        CounterStateWhenErroring()
    )
    override fun onEvent(event: CounterEvent) {}
}
