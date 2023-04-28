package com.wmccd.record_domain.external.fakes

import com.wmccd.common_models.external.records.RecordModel
import com.wmccd.record_repository.external.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecordRepository: RecordRepository  {

    override val records: Flow<List<RecordModel>> = flow{
        emit(
            listOf(
                RecordModel(id=1L, act = "A", title = "B"),
                RecordModel(id=2L, act = "C", title = "D"),
            )
        )
    }

    override val count: Flow<Int> = flow{
        emit(2)
    }

    override suspend fun insert(recordModel: RecordModel) {
    }

    override suspend fun update(recordModel: RecordModel): Int {
        return 1
    }

    override suspend fun delete(id: Long): Int {
        return 1
    }
}