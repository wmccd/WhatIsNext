package com.wmccd.weather_repository.external

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.request.RequestModel
import com.wmccd.datasource_services.external.WeatherAtLocationDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherAtLocationRepositoryImpl(
    private val dataSource: WeatherAtLocationDataSource,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ): WeatherAtLocationRepository {
    override suspend fun weatherAtLocation(requestModel: RequestModel) {
        withContext(dispatcher) {

            analogueReporter.report(
                action = AnalogueAction.Trace(
                    tag = "${this::class.simpleName}",
                    whatHappened = "Repository weather at location",
                    key = "Request",
                    value = "$requestModel"
                )
            )

            dataSource.weather(requestModel)
        }
    }
}