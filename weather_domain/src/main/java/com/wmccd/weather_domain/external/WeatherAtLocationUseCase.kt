package com.wmccd.weather_domain.external

import com.wmccd.common_models_types.external.models.weather.WeatherModel

interface WeatherAtLocationUseCase {
    suspend fun execute(
        success: (weatherModel: WeatherModel)-> Unit,
        failure: () -> Unit
    )
}