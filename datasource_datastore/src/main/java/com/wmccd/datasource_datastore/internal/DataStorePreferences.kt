package com.wmccd.datasource_datastore.internal

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DataStorePreferences (private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreConstants.DATASTORE_FILE_NAME)
    }

    private object Keys {
        object RecentRandomRecords {
            val RECENT_WINDOW_SIZE = intPreferencesKey("RECENT_WINDOW_SIZE")
        }
        object WeatherLocation {
            val LATITUDE = floatPreferencesKey("WEATHER_LOCATION_LATITUDE")
            val LONGITUDE = floatPreferencesKey("WEATHER_LOCATION_LONGITUDE")

        }
    }

    suspend fun recentRecordWindowSize( size: Int) {
        context.dataStore.edit { preferences ->
            preferences[Keys.RecentRandomRecords.RECENT_WINDOW_SIZE] = size
        }
    }
    val recentRecordWindowSize: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[Keys.RecentRandomRecords.RECENT_WINDOW_SIZE] ?: 0
        }


    suspend fun weatherLocationLatitude( latitude: Float) {
        context.dataStore.edit { preferences ->
            preferences[Keys.WeatherLocation.LATITUDE] = latitude
        }
    }
    val weatherLocationLatitude: Flow<Float> = context.dataStore.data
        .map { preferences ->
            preferences[Keys.WeatherLocation.LATITUDE] ?: 0.0F
        }

    suspend fun weatherLocationLongitude( longitude: Float) {
        context.dataStore.edit { preferences ->
            preferences[Keys.WeatherLocation.LONGITUDE] = longitude
        }
    }
    val weatherLocationLongitude: Flow<Float> = context.dataStore.data
        .map { preferences ->
            preferences[Keys.WeatherLocation.LONGITUDE] ?: 0.0F
        }


}