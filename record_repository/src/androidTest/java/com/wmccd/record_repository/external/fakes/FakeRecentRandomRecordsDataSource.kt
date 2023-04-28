package com.wmccd.record_repository.external.fakes

import com.wmccd.book_datasource.external.RecentRandomRecordDataSource
import com.wmccd.common_models.external.records.RecentRandomRecordModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecentRandomRecordsDataSource: RecentRandomRecordDataSource  {

    var lastInvoked = ""

    override val recentRandomRecords: Flow<List<RecentRandomRecordModel>> = flow{
        val list = listOf<RecentRandomRecordModel>(
            RecentRandomRecordModel( id = 1L, timestamp = 2L),
            RecentRandomRecordModel( id = 3L, timestamp = 4L),
        )
        lastInvoked = "fetch all"
        emit(list)
    }

    override val count: Flow<Int> = flow{
        lastInvoked = "count"
        emit(1)
    }

    override suspend fun insert(recentRandomRecordModel: RecentRandomRecordModel) {
        lastInvoked = "insert"
    }
}