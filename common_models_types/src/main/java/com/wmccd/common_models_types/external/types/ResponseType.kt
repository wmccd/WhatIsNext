package com.wmccd.common_models_types.external.types

import com.wmccd.common_models_types.external.models.weather.WeatherModel

sealed interface ResponseType {
    data class WeatherAtLocationResponse(val weatherModel: WeatherModel = WeatherModel()): ResponseType
    data class ErrorResponse(val code: Int = 0, val message: String = ""): ResponseType
}