package com.wmccd.datasource_datastore.external

import kotlinx.coroutines.flow.Flow

interface RecentRandomRecordSettingsDataSource {
    val recentRandomRecordsWindowSize: Flow<Int>
    suspend fun recentRandomRecordsWindowSize(size: Int)
}