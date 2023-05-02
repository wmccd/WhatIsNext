package com.wmccd.weather_domain

import com.wmccd.common_models_types.external.models.weather.WeatherModel

interface WeatherAtLocationUseCase {
    suspend fun execute(
        success: (WeatherModel)-> Unit,
        failure: () -> Unit
    )
}