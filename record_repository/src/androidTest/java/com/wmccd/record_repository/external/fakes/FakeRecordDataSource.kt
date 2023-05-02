package com.wmccd.record_repository.external.fakes

import com.wmccd.book_datasource.external.RecordDataSource
import com.wmccd.common_models_types.external.models.records.RecordModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRecordDataSource: RecordDataSource {

    var lastInvoked = ""


    override val records: Flow<List<RecordModel>> = flow {
        val list = listOf<RecordModel>(
            RecordModel(id = 1L, act = "A", title = "B"),
            RecordModel(id = 2L, act = "C", title = "D"),
        )
        lastInvoked = "fetch all"
        emit(list)
    }

    override val count: Flow<Int> = flow{
        lastInvoked = "count"

        emit(1)
    }

    override suspend fun insert(recordModel: RecordModel) {
        lastInvoked = "insert"
    }

    override suspend fun update(recordModel: RecordModel): Int {
        lastInvoked = "update"
        return 1
    }

    override suspend fun delete(id: Long): Int {
        lastInvoked = "delete"
        return 1
    }
}