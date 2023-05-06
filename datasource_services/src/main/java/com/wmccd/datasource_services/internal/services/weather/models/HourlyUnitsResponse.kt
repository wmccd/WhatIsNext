package com.wmccd.datasource_services.internal.services.weather.models


import com.google.gson.annotations.SerializedName

internal data class HourlyUnitsResponse(
    @SerializedName("precipitation_probability")
    val precipitationProbability: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("time")
    val time: String
)