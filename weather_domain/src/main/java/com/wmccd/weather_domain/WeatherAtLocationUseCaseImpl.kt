package com.wmccd.weather_domain

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.request.RequestModel
import com.wmccd.common_models_types.external.models.weather.WeatherModel
import com.wmccd.common_models_types.external.types.ResponseType
import com.wmccd.common_models_types.external.types.request.RequestContentType
import com.wmccd.common_models_types.external.types.request.RequestMethodType
import com.wmccd.weather_repository.external.WeatherLocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class WeatherAtLocationUseCaseImpl(
    private val weatherLocationRepository: WeatherLocationRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): WeatherAtLocationUseCase {
    override suspend fun execute(
        success: (WeatherModel)-> Unit,
        failure: () -> Unit
    ) {
        withContext(dispatcher) {
            val locationModel = weatherLocationRepository.location.first()
            val requestModel = RequestModel(
                methodType = RequestMethodType.GET,
                contentType = RequestContentType.ApplicationJson,
                url = "https://api.open-meteo.com/v1/forecast",
                headers = hashMapOf(),
                queryParameters = hashMapOf(),
                bodyMap = hashMapOf(),
                bodyJson = "{}",
                success = {
                    val response = it as ResponseType.WeatherAtLocationResponse
                    success(response.weatherModel)
                },
                failure = { _, _ -> }
            )


            analogueReporter.report(
                action = AnalogueAction.Trace(
                    tag = "${this::class.simpleName}",
                    whatHappened = "Repository: fetching weather",
                    key = "request",
                    value = "$requestModel"
                )
            )

            weatherLocationRepository
        }
    }

}