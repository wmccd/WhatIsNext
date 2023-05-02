package com.wmccd.weather_domain

import com.wmccd.common_models_types.external.models.weather.LocationModel
import kotlinx.coroutines.flow.Flow

interface WeatherLocationUseCase {
    val location: Flow<LocationModel>
}