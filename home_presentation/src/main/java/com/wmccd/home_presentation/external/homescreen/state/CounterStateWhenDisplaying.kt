package com.wmccd.home_presentation.external.homescreen.state

import androidx.compose.ui.graphics.Color

data class CounterStateWhenDisplaying(
    var title: String = "",
    var bookCountLabel: String = "",
    var bookCount: Int = 0,
    var recordCountLabel: String = "",
    var recordCount: Int = 0,
    var buttonLabel: String = "",
    var backgroundColor: Color = Color.LightGray
)
