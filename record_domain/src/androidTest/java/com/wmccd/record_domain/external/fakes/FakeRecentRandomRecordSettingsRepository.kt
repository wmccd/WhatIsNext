package com.wmccd.record_domain.external.fakes

import com.wmccd.record_repository.external.RecentRandomRecordSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecentRandomRecordSettingsRepository: RecentRandomRecordSettingsRepository {

    var windowSize = 0
    override val recentRandomRecordsWindowSize: Flow<Int> = flow {
        emit(windowSize)
    }

    override suspend fun recentRandomRecordsWindowSize(size: Int) {
        windowSize = size
    }
}