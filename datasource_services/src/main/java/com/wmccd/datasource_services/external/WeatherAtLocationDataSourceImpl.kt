package com.wmccd.datasource_services.external

import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.request.RequestModel
import com.wmccd.datasource_services.internal.services.weather.WeatherCall

class WeatherAtLocationDataSourceImpl(
    private val analogueReporter: AnalogueReporter
): WeatherAtLocationDataSource {
    override suspend fun weather(requestModel: RequestModel) {
        WeatherCall().execute(requestModel = requestModel)
    }

}