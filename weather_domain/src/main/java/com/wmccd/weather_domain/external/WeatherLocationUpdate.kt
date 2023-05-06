package com.wmccd.weather_domain.external

import com.wmccd.common_models_types.external.models.weather.LocationModel

interface WeatherLocationUpdate {
    suspend fun execute(locationModel: LocationModel)
}