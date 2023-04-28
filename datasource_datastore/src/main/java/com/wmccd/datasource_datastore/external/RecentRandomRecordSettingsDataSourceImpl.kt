package com.wmccd.datasource_datastore.external

import android.content.Context
import com.wmccd.analogue_reporter.external.AnalogueReporter
import com.wmccd.datasource_datastore.internal.DataStorePreferences
import kotlinx.coroutines.flow.Flow

class RecentRandomRecordSettingsDataSourceImpl(
    private val context: Context,
    private val analogueReporter: AnalogueReporter
): RecentRandomRecordSettingsDataSource {

    private val dataStorePreferences = DataStorePreferences(
        context = context
    )

    override val recentRandomRecordsWindowSize: Flow<Int> = dataStorePreferences.recentRecordWindowSize


    override suspend fun recentRandomRecordsWindowSize(size: Int) {
       dataStorePreferences.recentRecordWindowSize(size = size)
    }
}