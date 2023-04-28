package com.wmccd.record_repository.external

import com.wmccd.common_models.external.records.RecentRandomRecordModel
import kotlinx.coroutines.flow.Flow

interface RecentRandomRecordRepository {
    // Responsibility
    // defines the contract of behaviour with a data source (production or mocked)
    // public so that higher level classes can inject mocked implementations

    val recentRandomRecords : Flow<List<RecentRandomRecordModel>>
    val count : Flow<Int>

    suspend fun insert(recentRandomRecordModel: RecentRandomRecordModel)
}