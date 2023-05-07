package com.wmccd.home_presentation.external.fakes

import com.wmccd.common_models_types.external.models.weather.WeatherModel
import com.wmccd.weather_domain.external.WeatherAtLocationUseCase

class FakeWeatherAtLocationUseCase: WeatherAtLocationUseCase {
    override suspend fun execute(
        success: (weatherModel: WeatherModel) -> Unit,
        failure: () -> Unit
    ) {}
}