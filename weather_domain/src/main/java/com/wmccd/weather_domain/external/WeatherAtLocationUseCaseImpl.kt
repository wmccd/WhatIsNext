package com.wmccd.weather_domain.external

import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.request.RequestModel
import com.wmccd.common_models_types.external.models.weather.WeatherModel
import com.wmccd.common_models_types.external.types.ResponseType
import com.wmccd.common_models_types.external.types.request.RequestContentType
import com.wmccd.common_models_types.external.types.request.RequestMethodType
import com.wmccd.weather_repository.external.WeatherAtLocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class WeatherAtLocationUseCaseImpl(
    private val weatherLocationUseCase: WeatherLocationUseCase,
    private val weatherAtLocationRepository: WeatherAtLocationRepository,
    private val analogueReporter: AnalogueReporter,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): WeatherAtLocationUseCase {
    override suspend fun execute(
        success: (WeatherModel)-> Unit,
        failure: () -> Unit
    ){
        withContext(dispatcher) {
            val locationModel = weatherLocationUseCase.execute().first()
            val queryParameters = hashMapOf<String, String>()
            queryParameters["latitude"] = "54.5036169"//locationModel.latitude.toString()
            queryParameters["longitude"] = "-5.7976228" //locationModel.longitude.toString()
            queryParameters["hourly"] = "temperature_2m,precipitation_probability"

            val requestModel = RequestModel(
                methodType = RequestMethodType.GET,
                contentType = RequestContentType.ApplicationJson,
                urlDomain = "https://api.open-meteo.com/v1/",
                urlPath = "forcast",
                headers = hashMapOf(),
                queryParameters = queryParameters,
                bodyMap = hashMapOf(),
                bodyJson = "{}",
                success = {
                    val response = it as ResponseType.WeatherAtLocationResponse
                    success(response.weatherModel)
                },
                failure = {
                    failure()
                }
            )

            analogueReporter.report(
                action = AnalogueAction.Trace(
                    tag = "${this::class.simpleName}",
                    whatHappened = "Repository: fetching weather",
                    key = "request",
                    value = "$requestModel"
                )
            )

            weatherAtLocationRepository.weatherAtLocation(
                requestModel = requestModel
            )
        }
    }

}