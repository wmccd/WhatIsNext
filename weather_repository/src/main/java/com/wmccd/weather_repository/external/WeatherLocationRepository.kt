package com.wmccd.weather_repository.external

import com.wmccd.common_models_types.external.models.weather.LocationModel
import kotlinx.coroutines.flow.Flow

interface WeatherLocationRepository {
    val location: Flow<LocationModel>
    suspend fun location(locationModel: LocationModel)
}