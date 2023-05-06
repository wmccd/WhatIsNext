package com.wmccd.datasource_services.internal.services.weather.models


import com.google.gson.annotations.SerializedName

internal data class WeatherResponse(
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,
    @SerializedName("hourly")
    val hourly: HourlyResponse,
    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnitsResponse,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)