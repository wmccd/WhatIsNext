package com.wmccd.record_domain.external.fakes

import com.wmccd.common_models.external.records.RecentRandomRecordModel
import com.wmccd.record_repository.external.RecentRandomRecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecentRandomRecordRepository: RecentRandomRecordRepository {

    private val list = arrayListOf<RecentRandomRecordModel>(
        RecentRandomRecordModel( id = 1L, timestamp = 111L),
        RecentRandomRecordModel( id = 2L, timestamp = 222L),
        RecentRandomRecordModel( id = 3L, timestamp = 333L),
        RecentRandomRecordModel( id = 4L, timestamp = 444L),
    )

    override val recentRandomRecords: Flow<List<RecentRandomRecordModel>> = flow{
        emit(list)
    }

    override val count: Flow<Int> = flow{emit(list.size)}

    override suspend fun insert(recentRandomRecordModel: RecentRandomRecordModel) {
        list.add(recentRandomRecordModel)
    }
}