package com.wmccd.datasource_services.external

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.request.RequestModel
import com.wmccd.datasource_services.internal.services.weather.WeatherApi
import com.wmccd.datasource_services.internal.services.weather.WeatherCall

class WeatherAtLocationDataSourceImpl(
    private val analogueReporter: AnalogueReporter
): WeatherAtLocationDataSource {
    override suspend fun weather(requestModel: RequestModel) {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Data Source: weather at location",
                key = "Request model",
                value = "$requestModel"
            )
        )

        WeatherCall(
            analogueReporter = analogueReporter
        ).execute(requestModel = requestModel)
    }

}