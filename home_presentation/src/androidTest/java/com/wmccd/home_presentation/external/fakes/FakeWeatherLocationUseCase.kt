package com.wmccd.home_presentation.external.fakes

import com.wmccd.common_models_types.external.models.weather.LocationModel
import com.wmccd.weather_domain.external.WeatherLocationUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherLocationUseCase: WeatherLocationUseCase {
    override fun execute(): Flow<LocationModel> = flow{
        LocationModel(
            latitude = 1.0f,
            longitude = 2.0f
        )
    }
}