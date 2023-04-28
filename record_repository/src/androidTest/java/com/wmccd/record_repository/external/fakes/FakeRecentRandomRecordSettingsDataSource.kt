package com.wmccd.record_repository.external.fakes

import com.wmccd.datasource_datastore.external.RecentRandomRecordSettingsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecentRandomRecordSettingsDataSource: RecentRandomRecordSettingsDataSource {

    private var windowSize = 0

    override val recentRandomRecordsWindowSize: Flow<Int> = flow{ emit(windowSize) }

    override suspend fun recentRandomRecordsWindowSize(size: Int) {
        windowSize = size
    }
}