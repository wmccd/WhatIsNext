package com.wmccd.record_domain.external.fakes

import com.wmccd.common_models_types.external.models.records.RecordModel
import com.wmccd.record_repository.external.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecordRepositoryWithNoRecords: RecordRepository {
    override val records: Flow<List<RecordModel>> = flow{
        emit(
            listOf()
        )
    }

    override val count: Flow<Int> = flow{
        emit(0)
    }

    override suspend fun insert(recordModel: RecordModel) {
    }

    override suspend fun update(recordModel: RecordModel): Int {
        return 0
    }

    override suspend fun delete(id: Long): Int {
        return 0
    }
}