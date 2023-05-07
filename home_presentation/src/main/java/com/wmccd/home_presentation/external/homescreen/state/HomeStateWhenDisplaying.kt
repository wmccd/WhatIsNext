package com.wmccd.home_presentation.external.homescreen.state

import androidx.compose.ui.graphics.Color
import com.wmccd.common_models_types.external.models.weather.LocationModel
import com.wmccd.common_models_types.external.models.weather.WeatherModel

data class HomeStateWhenDisplaying(
    var title: String = "",
    var bookCountLabel: String = "",
    var bookCount: Int = 0,
    var recordCountLabel: String = "",
    var recordCount: Int = 0,
    var buttonLabel: String = "",
    var backgroundColor: Color = Color.LightGray,
    var weatherLocation: String = "",
    var weatherAtLocation: String = ""
)
