package com.wmccd.datasource_services.internal.services.weather

import com.wmccd.datasource_services.internal.services.weather.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query
import retrofit2.http.QueryMap

internal interface WeatherApi {

    @GET("forecast")
    suspend fun weather(
        @HeaderMap()headers: HashMap<String, String>,
        @QueryMap(encoded = false)queryParameters: HashMap<String, String>,
//        @Query("latitude")latitude: String,
//        @Query("longitude")longitude: String,
//        @Query("hourly")hourly: String,
    ) : Response<WeatherResponse>
}