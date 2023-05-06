package com.wmccd.datasource_services.internal.services.converters

import com.wmccd.common_models_types.external.models.weather.HourlyModel
import com.wmccd.common_models_types.external.models.weather.HourlyUnitsModel
import com.wmccd.common_models_types.external.models.weather.WeatherModel
import com.wmccd.datasource_services.internal.services.weather.models.HourlyResponse
import com.wmccd.datasource_services.internal.services.weather.models.HourlyUnitsResponse
import com.wmccd.datasource_services.internal.services.weather.models.WeatherResponse

internal class ConvertWeatherServiceAndCommonModels {

    fun convert(weatherResponse: WeatherResponse):WeatherModel =
        WeatherModel(
            elevation = weatherResponse.elevation,
            generationtimeMs = weatherResponse.generationtimeMs,
            hourly= convert(weatherResponse.hourly),
            hourlyUnits = convert(weatherResponse.hourlyUnits),
            latitude = weatherResponse.latitude,
            longitude = weatherResponse.longitude,
            timezone = weatherResponse.timezone,
            timezoneAbbreviation = weatherResponse.timezoneAbbreviation,
            utcOffsetSeconds = weatherResponse.utcOffsetSeconds
        )


    private fun convert(hourlyResponse: HourlyResponse): HourlyModel =
        HourlyModel(
            precipitationProbability = hourlyResponse.precipitationProbability,
            temperature2m = hourlyResponse.temperature2m,
            time = hourlyResponse.time
        )

    private fun convert(hourlyUnitsResponse: HourlyUnitsResponse): HourlyUnitsModel =
        HourlyUnitsModel(
            precipitationProbability = hourlyUnitsResponse.precipitationProbability,
            temperature2m = hourlyUnitsResponse.temperature2m,
            time = hourlyUnitsResponse.time

        )



}