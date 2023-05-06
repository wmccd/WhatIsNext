package com.wmccd.common_models_types.external.models.weather


import com.google.gson.annotations.SerializedName

data class HourlyUnitsModel(
    val precipitationProbability: String = "",
    val temperature2m: String = "",
    val time: String = ""
)