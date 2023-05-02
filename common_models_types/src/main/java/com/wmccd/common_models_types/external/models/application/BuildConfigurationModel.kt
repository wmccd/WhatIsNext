package com.wmccd.common_models_types.external.models.application

data class BuildConfigurationModel(
    val debug: Boolean = false,
    val buildType: String = "",
    val version_code: Int = 1
)


