package com.wmccd.record_repository.external

import kotlinx.coroutines.flow.Flow

interface RecentRandomRecordSettingsRepository {
    val recentRandomRecordsWindowSize: Flow<Int>
    suspend fun recentRandomRecordsWindowSize(size: Int)
}