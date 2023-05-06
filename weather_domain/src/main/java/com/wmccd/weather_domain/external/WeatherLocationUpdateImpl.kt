package com.wmccd.weather_domain.external

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.weather.LocationModel
import com.wmccd.weather_repository.external.WeatherLocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherLocationUpdateImpl(
    private val weatherLocationRepository: WeatherLocationRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): WeatherLocationUpdate {
    override suspend fun execute(locationModel: LocationModel) {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Domain: Weather Update",
                key = "location",
                value = "$locationModel"
            )
        )

        withContext(dispatcher){
            weatherLocationRepository.location(locationModel = locationModel)
        }
    }
}