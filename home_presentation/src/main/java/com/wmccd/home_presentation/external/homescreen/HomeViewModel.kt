package com.wmccd.home_presentation.external.homescreen

import androidx.compose.runtime.State
import com.wmccd.home_presentation.external.homescreen.event.CounterEvent
import com.wmccd.home_presentation.external.homescreen.state.HomeState
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenDisplaying
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenErroring
import com.wmccd.home_presentation.external.homescreen.state.HomeStateWhenLoading

interface HomeViewModel {
    val currentState: State<HomeState>
    val uiLoadingState: State<HomeStateWhenLoading>
    val uiDisplayState: State<HomeStateWhenDisplaying>
    val uiErroringState: State<HomeStateWhenErroring>
    fun onEvent(event: CounterEvent)
}