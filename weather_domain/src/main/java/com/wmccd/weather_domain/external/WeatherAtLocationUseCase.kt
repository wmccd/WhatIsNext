package com.wmccd.weather_domain.external

import com.wmccd.common_models_types.external.models.weather.WeatherModel
import com.wmccd.common_models_types.external.types.ResponseType
import kotlinx.coroutines.flow.Flow

interface WeatherAtLocationUseCase {
    suspend fun execute(
        success: (weatherModel: WeatherModel)-> Unit,
        failure: () -> Unit
    )
}