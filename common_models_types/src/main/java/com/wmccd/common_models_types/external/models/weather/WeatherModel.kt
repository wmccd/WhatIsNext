package com.wmccd.common_models_types.external.models.weather


data class WeatherModel(
    val elevation: Double = 0.0,
    val generationtimeMs: Double = 0.0,
    val hourly: HourlyModel = HourlyModel(),
    val hourlyUnits: HourlyUnitsModel = HourlyUnitsModel(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val timezone: String = "",
    val timezoneAbbreviation: String = "",
    val utcOffsetSeconds: Int = 0
)