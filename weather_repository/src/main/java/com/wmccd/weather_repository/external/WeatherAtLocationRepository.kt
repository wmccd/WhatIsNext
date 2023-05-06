package com.wmccd.weather_repository.external

import com.wmccd.common_models_types.external.models.request.RequestModel

interface WeatherAtLocationRepository {
    suspend fun weatherAtLocation(requestModel: RequestModel)
}