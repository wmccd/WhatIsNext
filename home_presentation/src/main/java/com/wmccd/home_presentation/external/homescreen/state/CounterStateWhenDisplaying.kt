package com.wmccd.home_presentation.external.homescreen.state

import androidx.compose.ui.graphics.Color

data class CounterStateWhenDisplaying(
    var title: String = "",
    var bookCountLabelColour: Color = Color.DarkGray,
    var bookCountLabel: String = "",
    var bookCount: Int = 0,
    var recordCountLabelColour: Color = Color.DarkGray,
    var recordCountLabel: String = "",
    var recordCount: Int = 0
)
