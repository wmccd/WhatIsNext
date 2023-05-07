package com.wmccd.home_presentation.external.homescreen

import com.wmccd.book_domain.external.usecases.BookCountUseCase
import com.wmccd.record_domain.external.usescases.RecordCountUseCase
import com.wmccd.weather_domain.external.WeatherAtLocationUseCase
import com.wmccd.weather_domain.external.WeatherLocationUseCase

data class HomeUseCases(
    val bookCountUseCase: BookCountUseCase,
    val recordCountUseCase: RecordCountUseCase,
    val weatherLocationUseCase: WeatherLocationUseCase,
    val weatherAtLocationUseCase: WeatherAtLocationUseCase,
)
