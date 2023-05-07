package com.wmccd.weather_domain.external

import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.weather.LocationModel
import com.wmccd.weather_repository.external.WeatherLocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherLocationUseCaseImpl(
    private val weatherLocationRepository: WeatherLocationRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher
): WeatherLocationUseCase {
    override fun execute(): Flow<LocationModel> = weatherLocationRepository.location

}