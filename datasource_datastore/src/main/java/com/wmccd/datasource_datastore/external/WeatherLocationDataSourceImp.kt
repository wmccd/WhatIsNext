package com.wmccd.datasource_datastore.external

import android.content.Context
import com.wmccd.analogue_reporter.external.AnalogueAction
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.common_models_types.external.models.weather.LocationModel
import com.wmccd.datasource_datastore.internal.DataStorePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class WeatherLocationDataSourceImp(
    private val context: Context,
    private val analogueReporter: AnalogueReporter
): WeatherLocationDataSource {

    private val dataStorePreferences = DataStorePreferences(
        context = context
    )

    override val weatherLocationModel: Flow<LocationModel> = flow {

        emit(
            LocationModel(
                latitude = dataStorePreferences.weatherLocationLatitude.first(),
                longitude = dataStorePreferences.weatherLocationLongitude.first()
            )
        )
    }


    override suspend fun weatherLocation(locationModel: LocationModel) {

        analogueReporter.report(
            action = AnalogueAction.Trace(
                tag = "${this::class.simpleName}",
                whatHappened = "DataStore: location updated",
                key = "lat and long",
                value = "$locationModel"
            )
        )

        dataStorePreferences.weatherLocationLatitude(latitude = locationModel.latitude)
        dataStorePreferences.weatherLocationLongitude(longitude = locationModel.longitude)

    }

}