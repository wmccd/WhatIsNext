package com.wmccd.datasource_datastore.external

import com.wmccd.common_models_types.external.models.weather.LocationModel
import kotlinx.coroutines.flow.Flow

interface WeatherLocationDataSource {
    val weatherLocationModel: Flow<LocationModel>
    suspend fun weatherLocation(locationModel: LocationModel)
}