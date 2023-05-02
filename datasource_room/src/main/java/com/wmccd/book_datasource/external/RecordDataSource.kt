package com.wmccd.book_datasource.external

import com.wmccd.common_models_types.external.models.records.RecordModel
import kotlinx.coroutines.flow.Flow

interface RecordDataSource {
    // Responsibility
    // defines the contract of behaviour with a data source (production or mocked)
    // public so that higher level classes can inject mocked implementations

    val records : Flow<List<RecordModel>>
    val count : Flow<Int>

    suspend fun insert(recordModel: RecordModel)
    suspend fun update(recordModel: RecordModel): Int
    suspend fun delete(id: Long): Int

}