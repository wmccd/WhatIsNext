package com.wmccd.weather_domain.external.fakes

import com.wmccd.common_models_types.external.models.weather.LocationModel
import com.wmccd.weather_repository.external.WeatherLocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherLocationRepository: WeatherLocationRepository {
    override val location: Flow<LocationModel> = flow{
        emit(
            LocationModel(
                latitude = 54.50f,
                longitude = 5.79f
            )
        )
    }

    override suspend fun location(locationModel: LocationModel) {}
}