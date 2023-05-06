package com.wmccd.datasource_services.internal.vendors.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun instance(base_url: String): Retrofit {
        return Retrofit.Builder().baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}