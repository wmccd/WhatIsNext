package com.wmccd.weather_domain

import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.weather.LocationModel
import com.wmccd.weather_repository.external.WeatherLocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class WeatherLocationUseCaseImpl(
    private val weatherLocationRepository: WeatherLocationRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher
): WeatherLocationUseCase {
    override val location: Flow<LocationModel> = weatherLocationRepository.location
}