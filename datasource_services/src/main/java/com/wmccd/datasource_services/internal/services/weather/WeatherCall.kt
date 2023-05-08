package com.wmccd.datasource_services.internal.services.weather

import android.util.Log
import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.request.RequestModel
import com.wmccd.common_models_types.external.models.weather.WeatherModel
import com.wmccd.common_models_types.external.types.ResponseType
import com.wmccd.datasource_services.internal.converters.ConvertWeatherServiceAndCommonModels
import com.wmccd.datasource_services.internal.vendors.retrofit.RetrofitHelper

class WeatherCall(
    private val analogueReporter: AnalogueReporter,
) {

    private val weatherApi =  WeatherApi::class.java
    suspend fun execute(requestModel: RequestModel){
        val weatherApi = RetrofitHelper
            .instance(requestModel.urlDomain)
            .create(weatherApi)

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "Before Service Call",
                key = "Request model?",
                value = "$requestModel"
            )
        )

        val result = weatherApi.invoke(
            headers = requestModel.headers,
            queryParameters = requestModel.queryParameters
        )

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "After Service Call",
                key = "Call succeeded?",
                value = ": ${result.isSuccessful}"
            )
        )
        if(!result.isSuccessful) {
            analogueReporter.report(
                action = AnalogueAction.Trace(
                    tag = "${this::class.simpleName}",
                    whatHappened = "After Service Call",
                    key = "Call Failure Details",
                    value = "<${result.code()}><${result.message()}>"
                )
            )
            requestModel.failure(
                ResponseType.ErrorResponse(
                    code = result.code(),
                    message = result.message()
                )
            )
            return
        }

        if(result.body() == null) {
            Log.d("XXX", "HERE3")
            requestModel.success(
                ResponseType.WeatherAtLocationResponse(
                    weatherModel = WeatherModel()
                )
            )
            return
        }

        val weatherModel: WeatherModel = try{
            ConvertWeatherServiceAndCommonModels().convert(
                weatherResponse = result.body()!!
            )
        }catch(ex: Exception){
            WeatherModel()
        }
        requestModel.success(
            ResponseType.WeatherAtLocationResponse(
                weatherModel = weatherModel
            )
        )
        result.body()?.let {
            ConvertWeatherServiceAndCommonModels().convert(
                it
            )
        }
    }
}