package com.wmccd.whatisnext.home.fakes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.wmccd.home_presentation.external.homescreen.HomeViewModel
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.HomeState
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenLoading

class FakeLoadingHomeViewModel(): HomeViewModel {
    override val currentState: State<HomeState> = mutableStateOf(HomeState.Loading)
    override val uiLoadingState: State<HomeStateWhenLoading> = mutableStateOf(
        HomeStateWhenLoading(
            message = "LOADING"
        )
    )
    override val uiDisplayState: State<HomeStateWhenDisplaying> = mutableStateOf(
        HomeStateWhenDisplaying()
    )
    override val uiErroringState: State<HomeStateWhenErroring> = mutableStateOf(
        HomeStateWhenErroring()
    )
    override fun onEvent(event: CounterEvent) {}
}
