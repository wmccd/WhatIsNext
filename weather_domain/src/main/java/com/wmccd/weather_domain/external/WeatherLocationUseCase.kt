package com.wmccd.weather_domain.external

import com.wmccd.common_models_types.external.models.weather.LocationModel
import kotlinx.coroutines.flow.Flow

interface WeatherLocationUseCase {
    fun execute(): Flow<LocationModel>
}