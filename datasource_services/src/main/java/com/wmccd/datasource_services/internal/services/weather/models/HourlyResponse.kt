package com.wmccd.datasource_services.internal.services.weather.models


import com.google.gson.annotations.SerializedName

internal data class HourlyResponse(
    @SerializedName("precipitation_probability")
    val precipitationProbability: List<Int>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("time")
    val time: List<String>
)