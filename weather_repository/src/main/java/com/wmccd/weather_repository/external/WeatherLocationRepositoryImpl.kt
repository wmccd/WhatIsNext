package com.wmccd.weather_repository.external

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.weather.LocationModel
import com.wmccd.datasource_datastore.external.WeatherLocationDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WeatherLocationRepositoryImpl  (
    private val dataSource: WeatherLocationDataSource,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): WeatherLocationRepository {

    override val location: Flow<LocationModel> = dataSource.weatherLocationModel

    override suspend fun location(locationModel: LocationModel) {
        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Repository: weather location updated",
                key = "location",
                value = "$locationModel"
            )
        )

        withContext(dispatcher) {
            dataSource.weatherLocation(locationModel = locationModel)
        }
    }
}