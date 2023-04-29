package com.wmccd.whatisnext.home.fakes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.wmccd.home_presentation.external.homescreen.CounterViewModel
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.CounterState
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.CounterStateWhenLoading

class FakeDisplayingViewModel(): CounterViewModel {

    var lastInvoked = ""

    override val currentState: State<CounterState> = mutableStateOf(CounterState.Displaying)
    override val uiLoadingState: State<CounterStateWhenLoading> = mutableStateOf(
        CounterStateWhenLoading()
    )
    override val uiDisplayState: State<CounterStateWhenDisplaying> = mutableStateOf(
        CounterStateWhenDisplaying(
            title = "Your Collection",
            bookCountLabel = "Book collection count",
            bookCount = 88,
            recordCountLabel = "Record collection count",
            recordCount = 132,
            buttonLabel = "Press Me Colours",
            backgroundColor = Color.LightGray
        )
    )
    override val uiErroringState: State<CounterStateWhenErroring> = mutableStateOf(
        CounterStateWhenErroring()
    )
    override fun onEvent(event: CounterEvent) {
        when(event){
            is CounterEvent.OnColorChangeButtonTapped -> lastInvoked = "button tapped"
        }
    }
}
