package com.wmccd.whatisnext.home.fakes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.wmccd.home_presentation.external.homescreen.HomeViewModel
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.HomeState
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenLoading

class FakeDisplayingViewModel(): HomeViewModel {

    var lastInvoked = ""

    override val currentState: State<HomeState> = mutableStateOf(HomeState.Displaying)
    override val uiLoadingState: State<HomeStateWhenLoading> = mutableStateOf(
        HomeStateWhenLoading()
    )
    override val uiDisplayState: State<HomeStateWhenDisplaying> = mutableStateOf(
        HomeStateWhenDisplaying(
            title = "Your Collection",
            bookCountLabel = "Book collection count",
            bookCount = 88,
            recordCountLabel = "Record collection count",
            recordCount = 132,
            buttonLabel = "Press Me Colours",
            backgroundColor = Color.LightGray
        )
    )
    override val uiErroringState: State<HomeStateWhenErroring> = mutableStateOf(
        HomeStateWhenErroring()
    )
    override fun onEvent(event: CounterEvent) {
        when(event){
            is CounterEvent.OnColorChangeButtonTapped -> lastInvoked = "button tapped"
        }
    }
}
