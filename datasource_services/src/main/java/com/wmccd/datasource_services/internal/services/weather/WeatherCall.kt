package com.wmccd.datasource_services.internal.services.weather

import com.wmccd.common_models_types.external.models.request.RequestModel
import com.wmccd.common_models_types.external.models.weather.WeatherModel
import com.wmccd.common_models_types.external.types.ResponseType
import com.wmccd.datasource_services.internal.services.converters.ConvertWeatherServiceAndCommonModels
import com.wmccd.datasource_services.internal.vendors.retrofit.RetrofitHelper

class WeatherCall {
    suspend fun execute(requestModel: RequestModel){
        val weatherApi = RetrofitHelper
            .instance(requestModel.urlDomain)
            .create(WeatherApi::class.java)
        val result = weatherApi.weather(
            headers = requestModel.headers,
            queryParameters = requestModel.queryParameters
        )

        if(!result.isSuccessful)
            requestModel.failure(result.code(), result.message())

        if(result.body() == null)
            requestModel.success(
                ResponseType.WeatherAtLocationResponse(
                    weatherModel = WeatherModel()
                )
            )
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