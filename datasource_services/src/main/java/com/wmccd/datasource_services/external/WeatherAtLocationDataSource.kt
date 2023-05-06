package com.wmccd.datasource_services.external

import com.wmccd.common_models_types.external.models.request.RequestModel

interface WeatherAtLocationDataSource {
    suspend fun weather(requestModel: RequestModel)
}