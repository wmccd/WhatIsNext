package com.wmccd.home_presentation.external.homescreen.state

sealed interface HomeState{
    object Loading: HomeState
    object Displaying: HomeState
    object Erroring: HomeState
}
