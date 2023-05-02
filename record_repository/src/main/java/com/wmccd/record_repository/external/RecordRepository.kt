package com.wmccd.record_repository.external

import com.wmccd.common_models_types.external.models.records.RecordModel
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    val records: Flow<List<RecordModel>>
    val count : Flow<Int>

    suspend fun insert(recordModel: RecordModel)
    suspend fun update(recordModel: RecordModel): Int
    suspend fun delete(id: Long): Int
}