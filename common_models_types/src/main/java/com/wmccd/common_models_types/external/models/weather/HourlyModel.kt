package com.wmccd.common_models_types.external.models.weather


import com.google.gson.annotations.SerializedName

data class HourlyModel(
    val precipitationProbability: List<Int> = listOf(),
    val temperature2m: List<Double> = listOf(),
    val time: List<String> = listOf()
)